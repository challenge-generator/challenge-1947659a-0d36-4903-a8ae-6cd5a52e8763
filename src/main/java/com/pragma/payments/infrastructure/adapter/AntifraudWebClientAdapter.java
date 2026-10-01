package com.pragma.payments.infrastructure.adapter;

import com.pragma.payments.domain.model.Transaction;
import com.pragma.payments.domain.port.AntifraudServicePort;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.reactor.circuitbreaker.operator.CircuitBreakerOperator;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryRegistry;
import io.github.resilience4j.retry.operator.RetryOperator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.function.Predicate;

@Component
@RequiredArgsConstructor
@Slf4j
public class AntifraudWebClientAdapter implements AntifraudServicePort {

    private final WebClient webClient;
    private final CircuitBreakerRegistry circuitBreakerRegistry;
    private final RetryRegistry retryRegistry;

    @Value("${external.services.antifraud.base-url:http://antifraud-service:8081}")
    private String antifraudBaseUrl;

    @Value("${external.services.antifraud.timeout-ms:5000}")
    private int timeoutMs;

    private static final String CIRCUIT_BREAKER_NAME = "antifraudService";
    private static final String RETRY_NAME = "antifraudServiceRetry";

    @Override
    public Mono<Transaction> evaluate(Transaction transaction) {
        log.debug("Evaluando transacción {} en servicio antifraude", transaction.getId());

        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker(CIRCUIT_BREAKER_NAME);
        Retry retry = retryRegistry.retry(RETRY_NAME);

        return webClient
                .post()
                .uri(antifraudBaseUrl + "/api/v1/evaluate")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(buildAntifraudRequest(transaction))
                .retrieve()
                .bodyToMono(AntifraudResponse.class)
                .timeout(Duration.ofMillis(timeoutMs), Mono.error(
                        new AntifraudTimeoutException("Timeout esperando respuesta del servicio antifraude")))
                .subscribeOn(Schedulers.boundedElastic())
                .transformDeferred(mono -> CircuitBreakerOperator.of(circuitBreaker).apply(mono))
                .transformDeferred(mono -> RetryOperator.of(retry).apply(mono))
                .onErrorResume(WebClientResponseException.ServiceUnavailable.class, e -> {
                    log.warn("Servicio antifraude no disponible: {}", e.getMessage());
                    return Mono.just(buildFallbackResponse(transaction, "SERVICE_UNAVAILABLE"));
                })
                .onErrorResume(WebClientResponseException.NotFound.class, e -> {
                    log.warn("Recurso antifraude no encontrado: {}", e.getMessage());
                    return Mono.just(buildFallbackResponse(transaction, "NOT_FOUND"));
                })
                .onErrorResume(Predicate.not(e -> e instanceof AntifraudResponseException.class), e -> {
                    log.error("Error inesperado en servicio antifraude: {}", e.getMessage());
                    return Mono.just(buildFallbackResponse(transaction, "ERROR"));
                })
                .map(response -> transaction.withAntifraudResponse(response.toJson()));
    }

    @Override
    public Mono<Boolean> isFraudulent(Transaction transaction) {
        log.debug("Verificando si transacción {} es fraudulenta", transaction.getId());

        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker(CIRCUIT_BREAKER_NAME);
        Retry retry = retryRegistry.retry(RETRY_NAME);

        return webClient
                .post()
                .uri(antifraudBaseUrl + "/api/v1/check-fraud")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(buildAntifraudRequest(transaction))
                .retrieve()
                .bodyToMono(FraudCheckResponse.class)
                .timeout(Duration.ofMillis(timeoutMs), Mono.error(
                        new AntifraudTimeoutException("Timeout en verificación de fraude")))
                .subscribeOn(Schedulers.boundedElastic())
                .transformDeferred(mono -> CircuitBreakerOperator.of(circuitBreaker).apply(mono))
                .transformDeferred(mono -> RetryOperator.of(retry).apply(mono))
                .onErrorResume(e -> {
                    log.error("Error en verificación de fraude, asumiendo no fraudulento: {}", e.getMessage());
                    return Mono.just(false);
                })
                .map(FraudCheckResponse::isFraudulent)
                .defaultIfEmpty(false);
    }

    @Override
    public Mono<Transaction> withFallbackEvaluation(Transaction transaction) {
        return evaluate(transaction)
                .onErrorResume(e -> {
                    log.warn("Evaluación con fallback para transacción {}: {}", transaction.getId(), e.getMessage());
                    return Mono.just(transaction.withAntifraudResponse("{\"status\":\"FALLBACK\"}"));
                });
    }

    private AntifraudRequest buildAntifraudRequest(Transaction transaction) {
        return new AntifraudRequest(
                transaction.getId().toString(),
                transaction.getOperationNumber(),
                transaction.getChannel(),
                transaction.getCreditOriginator(),
                transaction.getAccountNumber(),
                transaction.getAmount(),
                transaction.getCurrency()
        );
    }

    private AntifraudResponse buildFallbackResponse(Transaction transaction, String status) {
        return new AntifraudResponse(transaction.getId().toString(), status, "PENDING_REVIEW", 0.5);
    }

    private record AntifraudRequest(
            String transactionId,
            String operationNumber,
            String channel,
            String creditOriginator,
            String accountNumber,
            java.math.BigDecimal amount,
            String currency
    ) {}

    private record AntifraudResponse(
            String transactionId,
            String status,
            String riskLevel,
            double riskScore
    ) {
        String toJson() {
            return String.format("{\"transactionId\":\"%s\",\"status\":\"%s\",\"riskLevel\":\"%s\",\"riskScore\":%.2f}",
                    transactionId, status, riskLevel, riskScore);
        }
    }

    private record FraudCheckResponse(String transactionId, boolean fraudulent, String reason) {}

    private static class AntifraudResponseException extends RuntimeException {
        public AntifraudResponseException(String message) {
            super(message);
        }
    }

    private static class AntifraudTimeoutException extends RuntimeException {
        public AntifraudTimeoutException(String message) {
            super(message);
        }
    }
}
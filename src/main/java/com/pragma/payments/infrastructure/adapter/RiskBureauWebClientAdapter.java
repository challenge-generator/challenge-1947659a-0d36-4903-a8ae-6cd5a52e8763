package com.pragma.payments.infrastructure.adapter;

import com.pragma.payments.domain.model.Transaction;
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
public class RiskBureauWebClientAdapter {

    private final WebClient webClient;
    private final CircuitBreakerRegistry circuitBreakerRegistry;
    private final RetryRegistry retryRegistry;

    @Value("${external.services.risk-bureau.base-url:http://risk-bureau-service:8082}")
    private String riskBureauBaseUrl;

    @Value("${external.services.risk-bureau.timeout-ms:8000}")
    private int timeoutMs;

    private static final String CIRCUIT_BREAKER_NAME = "riskBureauService";
    private static final String RETRY_NAME = "riskBureauServiceRetry";

    public Mono<Transaction> evaluateRisk(Transaction transaction) {
        log.debug("Evaluando riesgo para transacción {}", transaction.getId());

        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker(CIRCUIT_BREAKER_NAME);
        Retry retry = retryRegistry.retry(RETRY_NAME);

        return webClient
                .post()
                .uri(riskBureauBaseUrl + "/api/v1/evaluate-risk")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(buildRiskEvaluationRequest(transaction))
                .retrieve()
                .bodyToMono(RiskEvaluationResponse.class)
                .timeout(Duration.ofMillis(timeoutMs), Mono.error(
                        new RiskBureauTimeoutException("Timeout evaluando riesgo")))
                .subscribeOn(Schedulers.boundedElastic())
                .transformDeferred(mono -> CircuitBreakerOperator.of(circuitBreaker).apply(mono))
                .transformDeferred(mono -> RetryOperator.of(retry).apply(mono))
                .onErrorResume(WebClientResponseException.ServiceUnavailable.class, e -> {
                    log.warn("Buró de riesgos no disponible: {}", e.getMessage());
                    return Mono.just(buildFallbackRiskResponse(transaction, "BUREAU_UNAVAILABLE"));
                })
                .onErrorResume(WebClientResponseException.BadRequest.class, e -> {
                    log.warn("Solicitud inválida al buró de riesgos: {}", e.getMessage());
                    return Mono.just(buildFallbackRiskResponse(transaction, "INVALID_REQUEST"));
                })
                .onErrorResume(Predicate.not(e -> e instanceof RiskBureauResponseException.class), e -> {
                    log.error("Error inesperado en buró de riesgos: {}", e.getMessage());
                    return Mono.just(buildFallbackRiskResponse(transaction, "ERROR"));
                })
                .map(response -> {
                    String riskJson = response.toJson();
                    return transaction.withRiskBureauResponse(riskJson);
                });
    }

    public Mono<RiskProfile> getRiskProfile(String creditOriginator, String accountNumber) {
        log.debug("Obteniendo perfil de riesgo para originador: {}, cuenta: {}", creditOriginator, accountNumber);

        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker(CIRCUIT_BREAKER_NAME);
        Retry retry = retryRegistry.retry(RETRY_NAME);

        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .pathSegment("api", "v1", "risk-profile")
                        .queryParam("originator", creditOriginator)
                        .queryParam("account", accountNumber)
                        .build())
                .retrieve()
                .bodyToMono(RiskProfileResponse.class)
                .timeout(Duration.ofMillis(timeoutMs), Mono.error(
                        new RiskBureauTimeoutException("Timeout obteniendo perfil de riesgo")))
                .subscribeOn(Schedulers.boundedElastic())
                .transformDeferred(mono -> CircuitBreakerOperator.of(circuitBreaker).apply(mono))
                .transformDeferred(mono -> RetryOperator.of(retry).apply(mono))
                .onErrorResume(e -> {
                    log.error("Error obteniendo perfil de riesgo, retornando perfil por defecto: {}", e.getMessage());
                    return Mono.just(RiskProfile.defaultProfile());
                })
                .map(RiskProfileResponse::toRiskProfile)
                .defaultIfEmpty(RiskProfile.defaultProfile());
    }

    public Mono<Boolean> isHighRisk(String creditOriginator) {
        log.debug("Verificando si originador {} es de alto riesgo", creditOriginator);

        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker(CIRCUIT_BREAKER_NAME);

        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .pathSegment("api", "v1", "high-risk")
                        .queryParam("originator", creditOriginator)
                        .build())
                .retrieve()
                .bodyToMono(HighRiskResponse.class)
                .timeout(Duration.ofMillis(timeoutMs), Mono.error(
                        new RiskBureauTimeoutException("Timeout verificando alto riesgo")))
                .subscribeOn(Schedulers.boundedElastic())
                .transformDeferred(mono -> CircuitBreakerOperator.of(circuitBreaker).apply(mono))
                .onErrorResume(e -> {
                    log.warn("Error verificando alto riesgo, asumiendo bajo riesgo: {}", e.getMessage());
                    return Mono.just(false);
                })
                .map(HighRiskResponse::isHighRisk)
                .defaultIfEmpty(false);
    }

    private RiskEvaluationRequest buildRiskEvaluationRequest(Transaction transaction) {
        return new RiskEvaluationRequest(
                transaction.getId().toString(),
                transaction.getCreditOriginator(),
                transaction.getAccountNumber(),
                transaction.getAmount(),
                transaction.getCurrency()
        );
    }

    private RiskEvaluationResponse buildFallbackRiskResponse(Transaction transaction, String status) {
        return new RiskEvaluationResponse(
                transaction.getId().toString(),
                status,
                "MEDIUM",
                0.5,
                "PENDING_MANUAL_REVIEW"
        );
    }

    private record RiskEvaluationRequest(
            String transactionId,
            String creditOriginator,
            String accountNumber,
            java.math.BigDecimal amount,
            String currency
    ) {}

    private record RiskEvaluationResponse(
            String transactionId,
            String status,
            String riskCategory,
            double riskScore,
            String recommendation
    ) {
        String toJson() {
            return String.format("{\"transactionId\":\"%s\",\"status\":\"%s\",\"riskCategory\":\"%s\",\"riskScore\":%.2f,\"recommendation\":\"%s\"}",
                    transactionId, status, riskCategory, riskScore, recommendation);
        }
    }

    private record RiskProfileResponse(
            String originator,
            String accountNumber,
            String riskCategory,
            double averageRiskScore,
            int totalTransactions,
            int flaggedTransactions
    ) {
        RiskProfile toRiskProfile() {
            return new RiskProfile(originator, accountNumber, riskCategory, averageRiskScore, totalTransactions, flaggedTransactions);
        }
    }

    private record HighRiskResponse(String originator, boolean highRisk, String reason) {}

    public record RiskProfile(
            String originator,
            String accountNumber,
            String riskCategory,
            double averageRiskScore,
            int totalTransactions,
            int flaggedTransactions
    ) {
        public static RiskProfile defaultProfile() {
            return new RiskProfile("UNKNOWN", "UNKNOWN", "MEDIUM", 0.5, 0, 0);
        }

        public boolean isHighRisk() {
            return "HIGH".equalsIgnoreCase(riskCategory) || averageRiskScore > 0.7;
        }
    }

    private static class RiskBureauResponseException extends RuntimeException {
        public RiskBureauResponseException(String message) {
            super(message);
        }
    }

    private static class RiskBureauTimeoutException extends RuntimeException {
        public RiskBureauTimeoutException(String message) {
            super(message);
        }
    }
}
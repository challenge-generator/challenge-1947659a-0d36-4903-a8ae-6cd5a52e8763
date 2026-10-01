package com.pragma.payments.application;

import com.pragma.payments.domain.model.Transaction;
import com.pragma.payments.domain.model.Transaction.TransactionStatus;
import com.pragma.payments.domain.port.AntifraudServicePort;
import com.pragma.payments.domain.port.RiskBureauPort;
import com.pragma.payments.domain.port.TransactionRepositoryPort;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.retry.Retry;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Servicio de aplicación que orquesta el procesamiento de transacciones.
 * Implementa el flujo reactivo de extremo a extremo integrando:
 * - Validación de idempotencia
 * - Evaluación antifraude
 * - Consulta de buró de riesgos
 * - Persistencia en base de datos reactiva
 * Utiliza operadores funcionales de Project Reactor para composición.
 */
@Service
@RequiredArgsConstructor
public class TransactionService {

    private static final Logger log = LoggerFactory.getLogger(TransactionService.class);
    private static final Duration IDEMPOTENCY_WINDOW = Duration.ofHours(24);

    private final TransactionRepositoryPort transactionRepository;
    private final AntifraudServicePort antifraudService;
    private final RiskBureauPort riskBureauPort;
    private final Scheduler boundedElasticScheduler;
    private final Scheduler parallelScheduler;
    private final CircuitBreaker antifraudCircuitBreaker;
    private final CircuitBreaker riskBureauCircuitBreaker;
    private final Retry transactionRetry;

    /**
     * Procesa una nueva transacción orchestrado el flujo completo.
     * El flujo es reactivo y tolerante a fallos con circuit breakers.
     * @param transaction la transacción a procesar
     * @return Mono con la transacción procesada
     */
    public Mono<Transaction> processTransaction(Transaction transaction) {
        log.info("Iniciando procesamiento de transacción: operationNumber={}, channel={}",
                transaction.getOperationNumber(), transaction.getChannel());

        return validateIdempotency(transaction)
            .flatMap(this::evaluateAntifraud)
            .flatMap(this::evaluateRiskBureau)
            .flatMap(this::persistTransaction)
            .doOnSuccess(result -> log.info("Transacción procesada exitosamente: id={}, status={}",
                    result.getId(), result.getStatus()))
            .doOnError(error -> log.error("Error en procesamiento de transacción: {}", error.getMessage()));
    }

    /**
     * Valida que la transacción sea idempotente dentro de la ventana de 24 horas.
     * Si ya existe una transacción con el mismo número de operación y canal,
     * retorna la transacción existente.
     */
    private Mono<Transaction> validateIdempotency(Transaction transaction) {
        if (transaction.getIdempotencyKey() == null || transaction.getIdempotencyKey().isBlank()) {
            return Mono.just(transaction);
        }

        return transactionRepository.existsByIdempotencyKey(transaction.getIdempotencyKey())
            .flatMap(exists -> {
                if (exists) {
                    log.info("Transacción idempotente detectada, recuperando existente: idempotencyKey={}",
                            transaction.getIdempotencyKey());
                    return transactionRepository
                        .findByOperationNumberAndChannel(
                            transaction.getOperationNumber(), 
                            transaction.getChannel())
                        .switchIfEmpty(Mono.just(transaction));
                }
                return Mono.just(transaction);
            });
    }

    /**
     * Evalúa la transacción mediante el servicio antifraude.
     * Utiliza circuit breaker para tolerar fallos del servicio externo.
     */
    private Mono<Transaction> evaluateAntifraud(Transaction transaction) {
        return Mono.fromCallable(() -> transaction)
            .subscribeOn(boundedElasticScheduler)
            .flatMap(tx -> Mono.defer(() -> antifraudService.evaluate(tx))
                .transformDeferred(mono -> decorateWithCircuitBreaker(mono, antifraudCircuitBreaker, "antifraud"))
                .retryWhen(transactionRetry))
            .doOnNext(result -> log.debug("Evaluación antifraude completada para transacción: {}", result.getId()))
            .onErrorResume(error -> {
                log.warn("Error en evaluación antifraude, continuando con respuesta vacía: {}", error.getMessage());
                return Mono.just(transaction.withAntifraudResponse("ERROR:" + error.getMessage()));
            });
    }

    /**
     * Evalúa el riesgo crediticio mediante el buró de riesgos.
     * Consulta el score de riesgo antes de procesar la transacción.
     */
    private Mono<Transaction> evaluateRiskBureau(Transaction transaction) {
        return riskBureauPort.getRiskScore(transaction.getCreditOriginator())
            .subscribeOn(parallelScheduler)
            .flatMap(riskScore -> {
                log.debug("Risk score para {}: {}", transaction.getCreditOriginator(), riskScore);
                if (riskScore > 0.7) {
                    log.warn("Riesgo excesivo detectado para originador: {}, score={}",
                            transaction.getCreditOriginator(), riskScore);
                    return Mono.just(transaction
                        .withRiskBureauResponse("HIGH_RISK:" + riskScore)
                        .withStatus(TransactionStatus.REJECTED));
                }
                return riskBureauPort.evaluateRisk(transaction)
                    .transformDeferred(mono -> decorateWithCircuitBreaker(mono, riskBureauCircuitBreaker, "riskBureau"))
                    .retryWhen(transactionRetry)
                    .onErrorResume(error -> {
                        log.warn("Error en buró de riesgos, continuando: {}", error.getMessage());
                        return Mono.just(transaction.withRiskBureauResponse("ERROR:" + error.getMessage()));
                    });
            })
            .switchIfEmpty(Mono.defer(() -> {
                log.warn("No se obtuvo risk score, continuando sin evaluación de riesgo");
                return Mono.just(transaction);
            }));
    }

    /**
     * Persiste la transacción en la base de datos.
     * El flujo de persistencia es reactivo y no bloqueante.
     */
    private Mono<Transaction> persistTransaction(Transaction transaction) {
        Transaction transactionToSave = transaction.getId() == null
            ? createNewTransaction(transaction)
            : transaction;

        return transactionRepository.save(transactionToSave)
            .subscribeOn(boundedElasticScheduler)
            .doOnNext(saved -> log.info("Transacción persistida: id={}, status={}", saved.getId(), saved.getStatus()));
    }

    /**
     * Crea una nueva transacción con ID generado y timestamps.
     */
    private Transaction createNewTransaction(Transaction base) {
        Transaction newTransaction = new Transaction();
        newTransaction.setId(UUID.randomUUID());
        newTransaction.setOperationNumber(base.getOperationNumber());
        newTransaction.setChannel(base.getChannel());
        newTransaction.setCreditOriginator(base.getCreditOriginator());
        newTransaction.setAccountNumber(base.getAccountNumber());
        newTransaction.setAmount(base.getAmount());
        newTransaction.setCurrency(base.getCurrency() != null ? base.getCurrency() : "USD");
        newTransaction.setCreatedAt(LocalDateTime.now());
        newTransaction.setStatus(TransactionStatus.PENDING);
        newTransaction.setAntifraudResponse(base.getAntifraudResponse());
        newTransaction.setRiskBureauResponse(base.getRiskBureauResponse());
        newTransaction.setPaymentGatewayResponse(base.getPaymentGatewayResponse());
        newTransaction.setIdempotencyKey(base.getIdempotencyKey());
        return newTransaction;
    }

    /**
     * Decora un Mono con circuit breaker de Resilience4j.
     */
    private <T> reactor.core.publisher.Mono<T> decorateWithCircuitBreaker(
            reactor.core.publisher.Mono<T> mono, 
            CircuitBreaker circuitBreaker,
            String serviceName) {
        return io.github.resilience4j.reactor.MonoCircuitBreaker.of(mono, circuitBreaker);
    }

    /**
     * Recupera una transacción por su ID.
     * @param id identificador de la transacción
     * @return Mono con la transacción encontrada o vacío
     */
    public Mono<Transaction> getTransactionById(UUID id) {
        return transactionRepository.findById(id)
            .subscribeOn(boundedElasticScheduler);
    }

    /**
     * Obtiene estadísticas de transacciones en un período.
     * @param cutoff fecha de corte para la consulta
     * @return Mono con el conteo de transacciones
     */
    public Mono<Long> getTransactionCountSince(LocalDateTime cutoff) {
        return transactionRepository.countByCreatedAtAfter(cutoff)
            .subscribeOn(parallelScheduler);
    }

    /**
     * Procesa múltiples transacciones en paralelo utilizando Flux.
     * @param transactions flujo de transacciones a procesar
     * @return Flux con los resultados del procesamiento
     */
    public reactor.core.publisher.Flux<Transaction> processBatch(
            reactor.core.publisher.Flux<Transaction> transactions) {
        return transactions
            .parallel()
            .runOn(parallelScheduler)
            .flatMap(this::processTransaction)
            .sequential();
    }

    /**
     * Cancela una transacción existente cambiando su estado.
     * @param id identificador de la transacción
     * @return Mono con la transacción actualizada
     */
    public Mono<Transaction> cancelTransaction(UUID id) {
        return getTransactionById(id)
            .flatMap(transaction -> {
                Transaction cancelled = transaction.withStatus(TransactionStatus.CANCELLED);
                return transactionRepository.save(cancelled);
            });
    }
}
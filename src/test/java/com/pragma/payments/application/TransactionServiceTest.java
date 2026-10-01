package com.pragma.payments.application;

import com.pragma.payments.domain.model.Transaction;
import com.pragma.payments.domain.model.Transaction.TransactionStatus;
import com.pragma.payments.domain.port.TransactionRepositoryPort;
import com.pragma.payments.domain.port.AntifraudServicePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests para TransactionService - Caso de uso de procesamiento de transacciones")
class TransactionServiceTest {

    @Mock
    private TransactionRepositoryPort transactionRepositoryPort;

    @Mock
    private AntifraudServicePort antifraudServicePort;

    private TransactionService transactionService;

    @BeforeEach
    void setUp() {
        transactionService = new TransactionService(transactionRepositoryPort, antifraudServicePort);
    }

    @Test
    @DisplayName("Debe procesar correctamente una transacción válida con evaluación antifraude")
    void shouldProcessValidTransactionWithAntifraudEvaluation() {
        Transaction transaction = createSampleTransaction();
        Transaction savedTransaction = transaction.withStatus(TransactionStatus.APPROVED);

        when(transactionRepositoryPort.existsByIdempotencyKey(anyString()))
                .thenReturn(Mono.just(false));
        when(antifraudServicePort.evaluate(any(Transaction.class)))
                .thenReturn(Mono.just(transaction.withAntifraudResponse("APPROVED")));
        when(transactionRepositoryPort.save(any(Transaction.class)))
                .thenReturn(Mono.just(savedTransaction));

        StepVerifier.create(transactionService.processTransaction(transaction))
                .expectNextMatches(t -> t.getStatus() == TransactionStatus.APPROVED
                        && "APPROVED".equals(t.getAntifraudResponse()))
                .verifyComplete();

        verify(transactionRepositoryPort).existsByIdempotencyKey(transaction.getIdempotencyKey());
        verify(antifraudServicePort).evaluate(any(Transaction.class));
        verify(transactionRepositoryPort).save(any(Transaction.class));
    }

    @Test
    @DisplayName("Debe rechazar transacción idempotente dentro de la ventana de tiempo")
    void shouldRejectIdempotentTransactionWithinWindow() {
        Transaction transaction = createSampleTransaction();
        Transaction existingTransaction = transaction.withStatus(TransactionStatus.APPROVED);

        when(transactionRepositoryPort.existsByIdempotencyKey(anyString()))
                .thenReturn(Mono.just(true));
        when(transactionRepositoryPort.findByOperationNumberAndChannel(
                transaction.getOperationNumber(), transaction.getChannel()))
                .thenReturn(Mono.just(existingTransaction));

        StepVerifier.create(transactionService.processTransaction(transaction))
                .expectErrorMatches(throwable ->
                        throwable instanceof IllegalArgumentException &&
                        throwable.getMessage().contains("Idempotency"))
                .verify();

        verify(transactionRepositoryPort, never()).save(any(Transaction.class));
        verify(antifraudServicePort, never()).evaluate(any(Transaction.class));
    }

    @Test
    @DisplayName("Debe manejar transacción marcada como fraudulenta por el servicio antifraude")
    void shouldHandleFraudulentTransaction() {
        Transaction transaction = createSampleTransaction();
        Transaction fraudTransaction = transaction.withAntifraudResponse("REJECTED")
                .withStatus(TransactionStatus.REJECTED);

        when(transactionRepositoryPort.existsByIdempotencyKey(anyString()))
                .thenReturn(Mono.just(false));
        when(antifraudServicePort.evaluate(any(Transaction.class)))
                .thenReturn(Mono.just(fraudTransaction));
        when(transactionRepositoryPort.save(any(Transaction.class)))
                .thenReturn(Mono.just(fraudTransaction));

        StepVerifier.create(transactionService.processTransaction(transaction))
                .expectNextMatches(t -> t.getStatus() == TransactionStatus.REJECTED
                        && "REJECTED".equals(t.getAntifraudResponse()))
                .verifyComplete();
    }

    @Test
    @DisplayName("Debe propagar error cuando el servicio antifraude falla")
    void shouldPropagateErrorWhenAntifraudServiceFails() {
        Transaction transaction = createSampleTransaction();

        when(transactionRepositoryPort.existsByIdempotencyKey(anyString()))
                .thenReturn(Mono.just(false));
        when(antifraudServicePort.evaluate(any(Transaction.class)))
                .thenReturn(Mono.error(new RuntimeException("Antifraud service unavailable")));

        StepVerifier.create(transactionService.processTransaction(transaction))
                .expectErrorMatches(throwable ->
                        throwable.getMessage().contains("Antifraud service unavailable"))
                .verify();

        verify(transactionRepositoryPort, never()).save(any(Transaction.class));
    }

    @Test
    @DisplayName("Debe validar que la transacción tenga datos requeridos antes del procesamiento")
    void shouldValidateRequiredTransactionData() {
        Transaction invalidTransaction = Transaction.builder()
                .id(UUID.randomUUID())
                .operationNumber(null)
                .channel("API")
                .amount(BigDecimal.valueOf(100))
                .currency("USD")
                .idempotencyKey(UUID.randomUUID().toString())
                .build();

        StepVerifier.create(transactionService.processTransaction(invalidTransaction))
                .expectError(IllegalArgumentException.class)
                .verify();
    }

    @Test
    @DisplayName("Debe buscar transacción existente por ID correctamente")
    void shouldFindTransactionById() {
        UUID transactionId = UUID.randomUUID();
        Transaction transaction = createSampleTransaction();

        when(transactionRepositoryPort.findById(transactionId))
                .thenReturn(Mono.just(transaction));

        StepVerifier.create(transactionService.findById(transactionId))
                .expectNext(transaction)
                .verifyComplete();
    }

    @Test
    @DisplayName("Debe retornar vacío al buscar transacción inexistente")
    void shouldReturnEmptyWhenTransactionNotFound() {
        UUID transactionId = UUID.randomUUID();

        when(transactionRepositoryPort.findById(transactionId))
                .thenReturn(Mono.empty());

        StepVerifier.create(transactionService.findById(transactionId))
                .verifyComplete();
    }

    @Test
    @DisplayName("Debe eliminar transacción por ID")
    void shouldDeleteTransactionById() {
        UUID transactionId = UUID.randomUUID();

        when(transactionRepositoryPort.deleteById(transactionId))
                .thenReturn(Mono.empty());

        StepVerifier.create(transactionService.deleteTransaction(transactionId))
                .verifyComplete();

        verify(transactionRepositoryPort).deleteById(transactionId);
    }

    private Transaction createSampleTransaction() {
        return Transaction.builder()
                .id(UUID.randomUUID())
                .operationNumber("OP-" + System.currentTimeMillis())
                .channel("API")
                .creditOriginator("CRED-001")
                .accountNumber("1234567890")
                .amount(BigDecimal.valueOf(1500.00))
                .currency("USD")
                .createdAt(LocalDateTime.now())
                .status(TransactionStatus.PENDING)
                .idempotencyKey(UUID.randomUUID().toString())
                .build();
    }
}
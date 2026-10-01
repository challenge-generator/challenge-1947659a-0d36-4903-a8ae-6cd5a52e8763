package com.pragma.payments.infrastructure.adapter;

import com.pragma.payments.domain.model.Transaction;
import com.pragma.payments.domain.model.Transaction.TransactionStatus;
import com.pragma.payments.domain.port.TransactionRepositoryPort;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.r2dbc.core.DatabaseClient;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Tests de integración para TransactionR2dbcAdapter con PostgreSQL")
class TransactionR2dbcAdapterTest {

    @Container
    private static final PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>(
            "postgres:16-alpine")
            .withDatabaseName("payments_test")
            .withUsername("test")
            .withPassword("test");

    private TransactionRepositoryPort adapter;
    private DatabaseClient databaseClient;

    @BeforeAll
    void setUp() {
        databaseClient = DatabaseClient.create(
                io.r2dbc.postgresql.PostgresqlConnectionConfiguration.builder()
                        .host(postgresContainer.getHost())
                        .port(postgresContainer.getMappedPort(5432))
                        .database("payments_test")
                        .username("test")
                        .password("test")
                        .build()
        );

        adapter = new TransactionR2dbcAdapter(databaseClient);

        createSchema();
    }

    private void createSchema() {
        databaseClient.sql("""
                CREATE TABLE IF NOT EXISTS transactions (
                    id UUID PRIMARY KEY,
                    operation_number VARCHAR(100) NOT NULL,
                    channel VARCHAR(50) NOT NULL,
                    credit_originator VARCHAR(100),
                    account_number VARCHAR(50),
                    amount DECIMAL(19,4) NOT NULL,
                    currency VARCHAR(3) NOT NULL,
                    created_at TIMESTAMP NOT NULL,
                    status VARCHAR(20) NOT NULL,
                    antifraud_response TEXT,
                    risk_bureau_response TEXT,
                    payment_gateway_response TEXT,
                    idempotency_key VARCHAR(100) UNIQUE
                )
                """).fetch().rowsUpdated().block();

        databaseClient.sql("""
                CREATE INDEX IF NOT EXISTS idx_transactions_operation_channel
                ON transactions(operation_number, channel)
                """).fetch().rowsUpdated().block();

        databaseClient.sql("""
                CREATE INDEX IF NOT EXISTS idx_transactions_created_at
                ON transactions(created_at)
                """).fetch().rowsUpdated().block();
    }

    @Test
    @DisplayName("Debe guardar una transacción correctamente en la base de datos")
    void shouldSaveTransactionSuccessfully() {
        Transaction transaction = createSampleTransaction();

        Mono<Transaction> saveResult = adapter.save(transaction);

        StepVerifier.create(saveResult)
                .assertThat(t -> {
                    assertThat(t.getId()).isEqualTo(transaction.getId());
                    assertThat(t.getOperationNumber()).isEqualTo(transaction.getOperationNumber());
                    assertThat(t.getChannel()).isEqualTo(transaction.getChannel());
                    assertThat(t.getAmount()).isEqualByComparingTo(transaction.getAmount());
                    assertThat(t.getStatus()).isEqualTo(transaction.getStatus());
                })
                .verifyComplete();
    }

    @Test
    @DisplayName("Debe recuperar una transacción por su ID")
    void shouldFindTransactionById() {
        Transaction transaction = createSampleTransaction();
        Transaction saved = adapter.save(transaction).block();

        assertThat(saved).isNotNull();

        StepVerifier.create(adapter.findById(saved.getId()))
                .assertThat(t -> {
                    assertThat(t.getId()).isEqualTo(saved.getId());
                    assertThat(t.getOperationNumber()).isEqualTo(transaction.getOperationNumber());
                })
                .verifyComplete();
    }

    @Test
    @DisplayName("Debe retornar vacío al buscar transacción inexistente")
    void shouldReturnEmptyForNonExistentTransaction() {
        UUID randomId = UUID.randomUUID();

        StepVerifier.create(adapter.findById(randomId))
                .verifyComplete();
    }

    @Test
    @DisplayName("Debe verificar existencia de clave idempotente")
    void shouldCheckIdempotencyKeyExists() {
        Transaction transaction = createSampleTransaction();
        String idempotencyKey = transaction.getIdempotencyKey();

        StepVerifier.create(adapter.existsByIdempotencyKey(idempotencyKey))
                .expectNext(false)
                .verifyComplete();

        adapter.save(transaction).block();

        StepVerifier.create(adapter.existsByIdempotencyKey(idempotencyKey))
                .expectNext(true)
                .verifyComplete();
    }

    @Test
    @DisplayName("Debe buscar transacción por número de operación y canal")
    void shouldFindByOperationNumberAndChannel() {
        Transaction transaction = createSampleTransaction();
        adapter.save(transaction).block();

        StepVerifier.create(adapter.findByOperationNumberAndChannel(
                transaction.getOperationNumber(),
                transaction.getChannel()))
                .assertThat(t -> {
                    assertThat(t.getOperationNumber()).isEqualTo(transaction.getOperationNumber());
                    assertThat(t.getChannel()).isEqualTo(transaction.getChannel());
                })
                .verifyComplete();
    }

    @Test
    @DisplayName("Debe eliminar transacción por ID")
    void shouldDeleteTransactionById() {
        Transaction transaction = createSampleTransaction();
        Transaction saved = adapter.save(transaction).block();

        assertThat(saved).isNotNull();

        StepVerifier.create(adapter.deleteById(saved.getId()))
                .verifyComplete();

        StepVerifier.create(adapter.findById(saved.getId()))
                .verifyComplete();
    }

    @Test
    @DisplayName("Debe contar transacciones creadas después de una fecha")
    void shouldCountTransactionsAfterDate() {
        LocalDateTime cutoff = LocalDateTime.now().minusHours(1);
        Transaction transaction = createSampleTransaction();
        adapter.save(transaction).block();

        StepVerifier.create(adapter.countByCreatedAtAfter(cutoff))
                .expectNext(1L)
                .verifyComplete();
    }

    @Test
    @DisplayName("Debe guardar múltiples transacciones y mantener integridad")
    void shouldSaveMultipleTransactionsMaintainingIntegrity() {
        Transaction t1 = createSampleTransaction();
        Transaction t2 = createSampleTransaction();
        Transaction t3 = createSampleTransaction();

        adapter.save(t1).block();
        adapter.save(t2).block();
        adapter.save(t3).block();

        LocalDateTime cutoff = LocalDateTime.now().minusHours(1);

        StepVerifier.create(adapter.countByCreatedAtAfter(cutoff))
                .expectNext(3L)
                .verifyComplete();
    }

    @Test
    @DisplayName("Debe rechazar clave idempotente duplicada")
    void shouldRejectDuplicateIdempotencyKey() {
        Transaction transaction = createSampleTransaction();
        String duplicateKey = transaction.getIdempotencyKey();

        adapter.save(transaction).block();

        Transaction duplicateTransaction = Transaction.builder()
                .id(UUID.randomUUID())
                .operationNumber("DIFFERENT-OP")
                .channel("API")
                .amount(BigDecimal.valueOf(200))
                .currency("USD")
                .createdAt(LocalDateTime.now())
                .status(TransactionStatus.PENDING)
                .idempotencyKey(duplicateKey)
                .build();

        StepVerifier.create(adapter.save(duplicateTransaction))
                .expectErrorMatches(t -> t.getMessage().contains("unique") ||
                        t.getMessage().contains("constraint"))
                .verify();
    }

    private Transaction createSampleTransaction() {
        return Transaction.builder()
                .id(UUID.randomUUID())
                .operationNumber("OP-" + UUID.randomUUID().toString().substring(0, 8))
                .channel("API")
                .creditOriginator("CRED-" + UUID.randomUUID().toString().substring(0, 4))
                .accountNumber("ACC" + System.currentTimeMillis())
                .amount(BigDecimal.valueOf(Math.random() * 10000))
                .currency("USD")
                .createdAt(LocalDateTime.now())
                .status(TransactionStatus.PENDING)
                .idempotencyKey("idem-" + UUID.randomUUID().toString())
                .build();
    }
}
package com.pragma.payments.infrastructure.adapter;

import com.pragma.payments.domain.model.Transaction;
import com.pragma.payments.domain.model.Transaction.TransactionStatus;
import com.pragma.payments.domain.port.TransactionRepositoryPort;
import io.r2dbc.postgresql.codec.Json;
import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import io.r2dbc.spi.Statement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Adaptador de infraestructura que implementa el puerto de persistencia
 * utilizando R2DBC (Reactive Relational Database Connectivity).
 * Proporciona operaciones reactivas y no bloqueantes sobre PostgreSQL.
 */
@Repository
@RequiredArgsConstructor
@Slf4j
public class TransactionR2dbcAdapter implements TransactionRepositoryPort {

    private final DatabaseClient databaseClient;

    private static final String INSERT_SQL = """
        INSERT INTO transactions 
            (id, operation_number, channel, credit_originator, account_number, 
             amount, currency, created_at, status, antifraud_response, 
             risk_bureau_response, payment_gateway_response, idempotency_key)
        VALUES ($1, $2, $3, $4, $5, $6, $7, $8, $9, $10, $11, $12, $13)
        """;

    private static final String SELECT_BY_ID_SQL = """
        SELECT id, operation_number, channel, credit_originator, account_number,
               amount, currency, created_at, status, antifraud_response,
               risk_bureau_response, payment_gateway_response, idempotency_key
        FROM transactions WHERE id = $1
        """;

    private static final String SELECT_BY_IDEMPOTENCY_KEY_SQL = """
        SELECT COUNT(*) FROM transactions WHERE idempotency_key = $1
        """;

    private static final String SELECT_BY_OPERATION_CHANNEL_SQL = """
        SELECT id, operation_number, channel, credit_originator, account_number,
               amount, currency, created_at, status, antifraud_response,
               risk_bureau_response, payment_gateway_response, idempotency_key
        FROM transactions 
        WHERE operation_number = $1 AND channel = $2
        ORDER BY created_at DESC LIMIT 1
        """;

    private static final String DELETE_SQL = "DELETE FROM transactions WHERE id = $1";

    private static final String COUNT_SINCE_SQL = 
        "SELECT COUNT(*) FROM transactions WHERE created_at > $1";

    @Override
    public Mono<Transaction> save(Transaction transaction) {
        log.debug("Persistiendo transacción: id={}, operationNumber={}",
                transaction.getId(), transaction.getOperationNumber());

        return databaseClient.sql(INSERT_SQL)
            .bind("$1", transaction.getId())
            .bind("$2", transaction.getOperationNumber())
            .bind("$3", transaction.getChannel())
            .bind("$4", transaction.getCreditOriginator())
            .bind("$5", transaction.getAccountNumber())
            .bind("$6", transaction.getAmount())
            .bind("$7", transaction.getCurrency())
            .bind("$8", transaction.getCreatedAt())
            .bind("$9", transaction.getStatus().name())
            .bind("$10", transaction.getAntifraudResponse() != null 
                ? Json.of(transaction.getAntifraudResponse()) 
                : Json.of(""))
            .bind("$11", transaction.getRiskBureauResponse() != null 
                ? Json.of(transaction.getRiskBureauResponse()) 
                : Json.of(""))
            .bind("$12", transaction.getPaymentGatewayResponse() != null 
                ? Json.of(transaction.getPaymentGatewayResponse()) 
                : Json.of(""))
            .bind("$13", transaction.getIdempotencyKey() != null 
                ? transaction.getIdempotencyKey() 
                : "")
            .fetch()
            .rowsUpdated()
            .flatMap(rows -> {
                log.info("Transacción guardada exitosamente: rows={}", rows);
                return Mono.just(transaction);
            })
            .onErrorResume(error -> {
                log.error("Error al persistir transacción: {}", error.getMessage());
                return Mono.error(error);
            });
    }

    @Override
    public Mono<Transaction> findById(UUID id) {
        log.debug("Buscando transacción por id: {}", id);

        return databaseClient.sql(SELECT_BY_ID_SQL)
            .bind("$1", id)
            .map(this::mapRowToTransaction)
            .first()
            .switchIfEmpty(Mono.defer(() -> {
                log.debug("No se encontró transacción con id: {}", id);
                return Mono.empty();
            }));
    }

    @Override
    public Mono<Boolean> existsByIdempotencyKey(String idempotencyKey) {
        if (idempotencyKey == null || idempotencyKey.isBlank()) {
            return Mono.just(false);
        }

        log.debug("Verificando idempotency key: {}", idempotencyKey);

        return databaseClient.sql(SELECT_BY_IDEMPOTENCY_KEY_SQL)
            .bind("$1", idempotencyKey)
            .map((row, metadata) -> row.get(0, Long.class))
            .first()
            .defaultIfEmpty(0L)
            .map(count -> count > 0);
    }

    @Override
    public Mono<Transaction> findByOperationNumberAndChannel(String operationNumber, String channel) {
        log.debug("Buscando transacción por operationNumber={}, channel={}", operationNumber, channel);

        return databaseClient.sql(SELECT_BY_OPERATION_CHANNEL_SQL)
            .bind("$1", operationNumber)
            .bind("$2", channel)
            .map(this::mapRowToTransaction)
            .first()
            .switchIfEmpty(Mono.empty());
    }

    @Override
    public Mono<Void> deleteById(UUID id) {
        log.debug("Eliminando transacción: {}", id);

        return databaseClient.sql(DELETE_SQL)
            .bind("$1", id)
            .fetch()
            .rowsUpdated()
            .flatMap(rows -> {
                log.info("Transacción eliminada: id={}, rows={}", id, rows);
                return Mono.empty();
            });
    }

    @Override
    public Mono<Long> countByCreatedAtAfter(LocalDateTime cutoff) {
        log.debug("Contando transacciones desde: {}", cutoff);

        return databaseClient.sql(COUNT_SINCE_SQL)
            .bind("$1", cutoff)
            .map((row, metadata) -> row.get(0, Long.class))
            .first()
            .defaultIfEmpty(0L);
    }

    /**
     * Mapea una fila de resultado a una entidad Transaction.
     * Maneja la conversión de tipos de PostgreSQL a tipos Java.
     */
    private Transaction mapRowToTransaction(Row row, RowMetadata metadata) {
        Transaction transaction = new Transaction();
        
        transaction.setId(row.get("id", UUID.class));
        transaction.setOperationNumber(row.get("operation_number", String.class));
        transaction.setChannel(row.get("channel", String.class));
        transaction.setCreditOriginator(row.get("credit_originator", String.class));
        transaction.setAccountNumber(row.get("account_number", String.class));
        transaction.setAmount(row.get("amount", BigDecimal.class));
        transaction.setCurrency(row.get("currency", String.class));
        transaction.setCreatedAt(row.get("created_at", LocalDateTime.class));
        
        String statusStr = row.get("status", String.class);
        if (statusStr != null) {
            transaction.setStatus(TransactionStatus.valueOf(statusStr));
        }
        
        Object antifraudObj = row.get("antifraud_response");
        if (antifraudObj != null) {
            transaction.setAntifraudResponse(extractJsonValue(antifraudObj));
        }
        
        Object riskBureauObj = row.get("risk_bureau_response");
        if (riskBureauObj != null) {
            transaction.setRiskBureauResponse(extractJsonValue(riskBureauObj));
        }
        
        Object paymentGatewayObj = row.get("payment_gateway_response");
        if (paymentGatewayObj != null) {
            transaction.setPaymentGatewayResponse(extractJsonValue(paymentGatewayObj));
        }
        
        transaction.setIdempotencyKey(row.get("idempotency_key", String.class));
        
        return transaction;
    }

    /**
     * Extrae el valor de un campo JSON almacenado en PostgreSQL.
     * R2DBC PostgreSQL devuelve objetos Json que deben ser procesados.
     */
    private String extractJsonValue(Object jsonObject) {
        if (jsonObject instanceof Json) {
            return ((Json) jsonObject).asString();
        }
        if (jsonObject instanceof String) {
            return (String) jsonObject;
        }
        return jsonObject != null ? jsonObject.toString() : null;
    }

    /**
     * Ejecuta una consulta SQL cruda para casos de uso avanzados.
     * Útil para consultas complejas que no encajan en los métodos estándar.
     */
    public Mono<Long> executeCountQuery(String sql) {
        return databaseClient.sql(sql)
            .map((row, metadata) -> row.get(0, Long.class))
            .first()
            .defaultIfEmpty(0L);
    }
}
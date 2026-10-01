package com.pragma.payments.domain.port;

import com.pragma.payments.domain.model.Transaction;
import reactor.core.publisher.Mono;
import java.time.LocalDateTime;
import java.util.UUID;

public interface TransactionRepositoryPort {
    Mono<Transaction> save(Transaction transaction);
    
    Mono<Transaction> findById(UUID id);
    
    Mono<Boolean> existsByIdempotencyKey(String idempotencyKey);
    
    Mono<Transaction> findByOperationNumberAndChannel(String operationNumber, String channel);
    
    Mono<Void> deleteById(UUID id);
    
    Mono<Long> countByCreatedAtAfter(LocalDateTime cutoff);
}
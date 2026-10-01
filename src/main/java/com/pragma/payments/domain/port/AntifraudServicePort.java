package com.pragma.payments.domain.port;

import com.pragma.payments.domain.model.Transaction;
import reactor.core.publisher.Mono;

public interface AntifraudServicePort {
    Mono<Transaction> evaluate(Transaction transaction);
    
    Mono<Boolean> isFraudulent(Transaction transaction);
    
    default Mono<Transaction> withFallbackEvaluation(Transaction transaction) {
        return evaluate(transaction)
                .onErrorResume(e -> Mono.just(transaction.withAntifraudResponse("Fallback: " + e.getMessage())));
    }
}
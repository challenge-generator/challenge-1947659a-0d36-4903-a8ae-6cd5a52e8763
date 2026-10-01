package com.pragma.payments.domain.model;

import lombok.Builder;
import lombok.Value;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Value
@Builder(toBuilder = true)
public class Transaction {
    UUID id;
    String operationNumber;
    String channel;
    String creditOriginator;
    String accountNumber;
    BigDecimal amount;
    String currency;
    LocalDateTime createdAt;
    TransactionStatus status;
    String antifraudResponse;
    String riskBureauResponse;
    String paymentGatewayResponse;
    String idempotencyKey;

    public enum TransactionStatus {
        PENDING,
        APPROVED,
        REJECTED,
        FRAUD_DETECTED,
        RISK_REJECTED,
        PAYMENT_FAILED,
        COMPLETED
    }

    public boolean isIdempotentWithinWindow(String operationNumber, String channel, LocalDateTime windowStart) {
        return this.operationNumber.equals(operationNumber) 
                && this.channel.equals(channel) 
                && this.createdAt.isAfter(windowStart);
    }

    public Transaction withAntifraudResponse(String response) {
        return this.toBuilder().antifraudResponse(response).build();
    }

    public Transaction withRiskBureauResponse(String response) {
        return this.toBuilder().riskBureauResponse(response).build();
    }

    public Transaction withPaymentGatewayResponse(String response) {
        return this.toBuilder().paymentGatewayResponse(response).build();
    }

    public Transaction withStatus(TransactionStatus newStatus) {
        return this.toBuilder().status(newStatus).build();
    }
}
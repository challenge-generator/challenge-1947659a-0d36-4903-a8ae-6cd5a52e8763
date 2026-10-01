package com.pragma.payments.infrastructure.controller.dto;

import com.pragma.payments.domain.model.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record TransactionResponse(
        UUID id,
        String operationNumber,
        String channel,
        String creditOriginator,
        String accountNumber,
        BigDecimal amount,
        String currency,
        LocalDateTime createdAt,
        String status,
        String antifraudResponse,
        String riskBureauResponse,
        String paymentGatewayResponse
) {
    public static TransactionResponse fromDomain(Transaction transaction) {
        return new TransactionResponse(
                transaction.getId(),
                transaction.getOperationNumber(),
                transaction.getChannel(),
                transaction.getCreditOriginator(),
                transaction.getAccountNumber(),
                transaction.getAmount(),
                transaction.getCurrency(),
                transaction.getCreatedAt(),
                transaction.getStatus().name(),
                transaction.getAntifraudResponse(),
                transaction.getRiskBureauResponse(),
                transaction.getPaymentGatewayResponse()
        );
    }
}
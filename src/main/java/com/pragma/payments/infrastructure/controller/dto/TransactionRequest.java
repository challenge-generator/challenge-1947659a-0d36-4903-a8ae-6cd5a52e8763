package com.pragma.payments.infrastructure.controller.dto;



import com.pragma.payments.domain.model.TransactionStatus;
import com.pragma.payments.domain.model.Transaction;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record TransactionRequest(
        @NotBlank(message = "Operation number is required")
        String operationNumber,
        
        @NotBlank(message = "Channel is required")
        @Size(max = 50)
        String channel,
        
        @NotBlank(message = "Credit originator is required")
        String creditOriginator,
        
        @NotBlank(message = "Account number is required")
        String accountNumber,
        
        @NotNull(message = "Amount is required")
        @Positive(message = "Amount must be positive")
        BigDecimal amount,
        
        @NotBlank(message = "Currency is required")
        @Size(min = 3, max = 3)
        String currency
) {
    public com.pragma.payments.domain.model.Transaction toDomain() {
        return com.pragma.payments.domain.model.Transaction.builder()
                .id(UUID.randomUUID())
                .operationNumber(operationNumber)
                .channel(channel)
                .creditOriginator(creditOriginator)
                .accountNumber(accountNumber)
                .amount(amount)
                .currency(currency)
                .createdAt(LocalDateTime.now())
                .status(com.pragma.payments.domain.model.Transaction.TransactionStatus.PENDING)
                .build();
    }
}
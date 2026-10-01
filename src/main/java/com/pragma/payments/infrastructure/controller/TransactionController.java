package com.pragma.payments.infrastructure.controller;

import com.pragma.payments.application.TransactionService;
import com.pragma.payments.infrastructure.controller.dto.TransactionRequest;
import com.pragma.payments.infrastructure.controller.dto.TransactionResponse;
import com.pragma.payments.infrastructure.controller.dto.ErrorResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ExceptionHandler;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public Mono<ResponseEntity<TransactionResponse>> createTransaction(
            @Valid @RequestBody TransactionRequest request,
            @RequestHeader(value = "Idempotency-Key", required = false) String idempotencyKey) {
        
        return transactionService.processTransaction(request.toDomain(), idempotencyKey)
                .map(transaction -> ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(TransactionResponse.fromDomain(transaction)))
                .onErrorResume(DuplicateTransactionException.class, e ->
                        Mono.just(ResponseEntity
                                .status(HttpStatus.CONFLICT)
                                .body(new ErrorResponse(
                                        "DUPLICATE_TRANSACTION",
                                        e.getMessage(),
                                        LocalDateTime.now())))
                                .map(r -> (ResponseEntity<TransactionResponse>) ResponseEntity.status(409).build()))
                .onErrorResume(ExternalServiceException.class, e ->
                        Mono.just(ResponseEntity
                                .status(HttpStatus.SERVICE_UNAVAILABLE)
                                .body(new ErrorResponse(
                                        "EXTERNAL_SERVICE_ERROR",
                                        e.getMessage(),
                                        LocalDateTime.now())))
                                .map(r -> (ResponseEntity<TransactionResponse>) ResponseEntity.status(503).build()));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<TransactionResponse>> getTransaction(@PathVariable UUID id) {
        return transactionService.findById(id)
                .map(transaction -> ResponseEntity.ok(TransactionResponse.fromDomain(transaction)))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping
    public Flux<TransactionResponse> getAllTransactions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return transactionService.findAll(page, size)
                .map(TransactionResponse::fromDomain);
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteTransaction(@PathVariable UUID id) {
        return transactionService.deleteById(id)
                .then(Mono.just(ResponseEntity.noContent().build()))
                .onErrorResume(e -> Mono.just(ResponseEntity.notFound().build()));
    }

    @GetMapping("/health")
    public Mono<ResponseEntity<String>> healthCheck() {
        return Mono.just(ResponseEntity.ok("UP"));
    }

    static class DuplicateTransactionException extends RuntimeException {
        public DuplicateTransactionException(String message) {
            super(message);
        }
    }

    static class ExternalServiceException extends RuntimeException {
        public ExternalServiceException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
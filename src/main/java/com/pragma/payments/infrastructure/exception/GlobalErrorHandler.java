package com.pragma.payments.infrastructure.exception;

import com.pragma.payments.infrastructure.controller.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(WebExchangeBindException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleValidationError(WebExchangeBindException ex) {
        String errors = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));
        
        ErrorResponse errorResponse = new ErrorResponse(
                "VALIDATION_ERROR",
                errors,
                LocalDateTime.now()
        );
        
        return Mono.just(ResponseEntity.badRequest().body(errorResponse));
    }

    @ExceptionHandler(ResponseStatusException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleResponseStatusException(ResponseStatusException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                "HTTP_ERROR",
                ex.getReason() != null ? ex.getReason() : ex.getMessage(),
                LocalDateTime.now()
        );
        
        return Mono.just(ResponseEntity.status(ex.getStatusCode()).body(errorResponse));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleIllegalArgument(IllegalArgumentException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                "BAD_REQUEST",
                ex.getMessage(),
                LocalDateTime.now()
        );
        
        return Mono.just(ResponseEntity.badRequest().body(errorResponse));
    }

    @ExceptionHandler(IllegalStateException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleIllegalState(IllegalStateException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                "CONFLICT",
                ex.getMessage(),
                LocalDateTime.now()
        );
        
        return Mono.just(ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse));
    }

    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<ErrorResponse>> handleGenericException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                "INTERNAL_ERROR",
                "An unexpected error occurred: " + ex.getMessage(),
                LocalDateTime.now()
        );
        
        return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse));
    }

    @ExceptionHandler(TransactionNotFoundException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleTransactionNotFound(TransactionNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                "NOT_FOUND",
                ex.getMessage(),
                LocalDateTime.now()
        );
        
        return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse));
    }

    @ExceptionHandler(DuplicateTransactionException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleDuplicateTransaction(DuplicateTransactionException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                "DUPLICATE_TRANSACTION",
                ex.getMessage(),
                LocalDateTime.now()
        );
        
        return Mono.just(ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse));
    }

    @ExceptionHandler(ExternalServiceException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleExternalService(ExternalServiceException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                "EXTERNAL_SERVICE_UNAVAILABLE",
                ex.getMessage(),
                LocalDateTime.now()
        );
        
        return Mono.just(ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(errorResponse));
    }

    public static class TransactionNotFoundException extends RuntimeException {
        public TransactionNotFoundException(String message) {
            super(message);
        }
    }

    public static class DuplicateTransactionException extends RuntimeException {
        public DuplicateTransactionException(String message) {
            super(message);
        }
    }

    public static class ExternalServiceException extends RuntimeException {
        public ExternalServiceException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
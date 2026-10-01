package com.pragma.payments.domain.port;

import com.pragma.payments.domain.model.Transaction;
import reactor.core.publisher.Mono;

/**
 * Puerto del dominio para integración con el buró de riesgos.
 * Define el contrato que la capa de aplicación utiliza para evaluar
 * el riesgo crediticio de una transacción antes de procesarla.
 * La implementación concreta se inyecta en tiempo de ejecución.
 */
public interface RiskBureauPort {

    /**
     * Evalúa el riesgo crediticio asociado a una transacción.
     * Retorna la transacción con la respuesta del buró de riesgos adjunta.
     * @param transaction la transacción a evaluar
     * @return Mono con la transacción actualizada incluyendo la respuesta del buró
     */
    Mono<Transaction> evaluateRisk(Transaction transaction);

    /**
     * Consulta el historial de riesgos para un originador de crédito.
     * @param creditOriginator identificador del originador de crédito
     * @return Mono con el nivel de riesgo calculado (0.0 a 1.0)
     */
    Mono<Double> getRiskScore(String creditOriginator);

    /**
     * Verifica si un originador de crédito estábloqueado por riesgo excesivo.
     * @param creditOriginator identificador del originador
     * @return Mono verdadero si el originador estábloqueado
     */
    Mono<Boolean> isBlocked(String creditOriginator);

    /**
     * Método de conveniencia que evalúa riesgo con fallback automático.
     * Si el buró de riesgos falla, retorna la transacción sin respuesta
     * pero permite continuar el flujo (soft failure).
     * @param transaction la transacción a evaluar
     * @return Mono con la transacción evaluada o la original en caso de error
     */
    default Mono<Transaction> withFallbackEvaluation(Transaction transaction) {
        return evaluateRisk(transaction)
            .onErrorResume(error -> {
                return Mono.just(transaction);
            });
    }
}
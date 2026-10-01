package com.pragma.payments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.scheduler.Schedulers;
import java.time.Duration;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl("http://localhost:8080")
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(16 * 1024 * 1024))
                .build();
    }

    @Bean
    public reactor.core.scheduler.Scheduler boundedElasticScheduler() {
        return Schedulers.newBoundedElastic(10, 100, "bounded-elastic");
    }

    @Bean
    public reactor.core.scheduler.Scheduler singleScheduler() {
        return Schedulers.newSingle("single-scheduler");
    }

    @Bean
    public reactor.core.scheduler.Scheduler parallelScheduler() {
        return Schedulers.newParallel("parallel-scheduler", 4);
    }

    @Bean
    public io.github.resilience4j.circuitbreaker.CircuitBreakerConfig customCircuitBreakerConfig() {
        return io.github.resilience4j.circuitbreaker.CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .waitDurationInOpenState(Duration.ofMillis(1000))
                .permittedNumberOfCallsInHalfOpenState(3)
                .slidingWindowSize(10)
                .recordExceptions(
                    java.io.IOException.class,
                    org.springframework.web.client.HttpServerErrorException.class,
                    org.springframework.web.reactive.function.client.WebClientResponseException.class
                )
                .build();
    }

    @Bean
    public io.github.resilience4j.retry.RetryConfig customRetryConfig() {
        return io.github.resilience4j.retry.RetryConfig.custom()
                .maxAttempts(3)
                .waitDuration(Duration.ofMillis(500))
                .retryExceptions(
                    java.io.IOException.class,
                    org.springframework.web.client.HttpServerErrorException.class,
                    org.springframework.web.reactive.function.client.WebClientResponseException.class
                )
                .build();
    }

    @Bean
    public io.github.resilience4j.bulkhead.BulkheadConfig customBulkheadConfig() {
        return io.github.resilience4j.bulkhead.BulkheadConfig.custom()
                .maxConcurrentCalls(25)
                .maxWaitDuration(Duration.ofMillis(100))
                .build();
    }
}
package com.pragma.payments.infrastructure.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Configuration
@Slf4j
public class WebClientConfig {

    @Value("${webclient.connection.max-connections:200}")
    private int maxConnections;

    @Value("${webclient.connection.pending-acquire-timeout-ms:60000}")
    private int pendingAcquireTimeoutMs;

    @Value("${webclient.connection.max-idle-time-ms:20000}")
    private int maxIdleTimeMs;

    @Value("${webclient.connection.max-life-time-ms:60000}")
    private int maxLifeTimeMs;

    @Value("${webclient.connection.pending-acquire-max-count:200}")
    private int pendingAcquireMaxCount;

    @Value("${webclient.connection.evict-in-background-period-ms:30000}")
    private int evictInBackgroundPeriodMs;

    @Value("${webclient.response.max-in-memory-size-bytes:16777216}")
    private int maxInMemorySizeBytes;

    @Value("${webclient.connect.timeout-ms:10000}")
    private int connectTimeoutMs;

    @Value("${webclient.response.timeout-ms:30000}")
    private int responseTimeoutMs;

    @Bean
    public ConnectionProvider connectionProvider() {
        log.info("Configurando ConnectionProvider con maxConnections={}, pendingAcquireTimeout={}ms",
                maxConnections, pendingAcquireTimeoutMs);

        return ConnectionProvider.builder("paymentsConnectionPool")
                .maxConnections(maxConnections)
                .pendingAcquireTimeout(Duration.ofMillis(pendingAcquireTimeoutMs))
                .maxIdleTime(Duration.ofMillis(maxIdleTimeMs))
                .maxLifeTime(Duration.ofMillis(maxLifeTimeMs))
                .pendingAcquireMaxCount(pendingAcquireMaxCount)
                .evictInBackground(Duration.ofMillis(evictInBackgroundPeriodMs))
                .metricsEnabled(true)
                .build();
    }

    @Bean
    public HttpClient httpClient(ConnectionProvider connectionProvider) {
        log.info("Configurando HttpClient con connectTimeout={}ms, responseTimeout={}ms",
                connectTimeoutMs, responseTimeoutMs);

        return HttpClient.create(connectionProvider)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, connectTimeoutMs)
                .responseTimeout(Duration.ofMillis(responseTimeoutMs))
                .doOnConnected(conn -> conn
                        .addHandlerLast(new ReadTimeoutHandler(responseTimeoutMs, TimeUnit.MILLISECONDS))
                        .addHandlerLast(new WriteTimeoutHandler(responseTimeoutMs, TimeUnit.MILLISECONDS)));
    }

    @Bean
    public WebClient webClient(HttpClient httpClient) {
        log.info("Configurando WebClient con maxInMemorySize={} bytes", maxInMemorySizeBytes);

        ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
                .codecs(configurer -> configurer
                        .defaultCodecs()
                        .maxInMemorySize(maxInMemorySizeBytes))
                .build();

        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .exchangeStrategies(exchangeStrategies)
                .defaultHeader("Accept", "application/json")
                .defaultHeader("Content-Type", "application/json")
                .filter((request, next) -> {
                    log.debug("Enviando solicitud a: {} {}", request.method(), request.url());
                    return next.exchange(request)
                            .doOnNext(response -> {
                                log.debug("Respuesta recibida: {} {}", response.statusCode().value(), response.statusCode().reasonPhrase());
                            })
                            .doOnError(error -> {
                                log.error("Error en solicitud HTTP: {}", error.getMessage());
                            });
                })
                .build();
    }
}
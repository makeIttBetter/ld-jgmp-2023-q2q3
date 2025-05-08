package com.example.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.UUID;

/**
 * A GlobalFilter that ensures every request has a traceId.
 * If X-Trace-ID is missing from the request, it generates one.
 * It also puts traceId into MDC for logging, and propagates
 * the header to downstream services.
 */
@Slf4j
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class TraceIdGlobalFilter implements GlobalFilter, Ordered {

    private static final String TRACE_ID_HEADER = "X-Trace-ID";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        // 1) Check if there's already a traceId in the request headers
        String existingTraceId = exchange.getRequest().getHeaders().getFirst(TRACE_ID_HEADER);

        // 2) If not present, generate a new one
        String traceId = Optional.ofNullable(existingTraceId)
                .filter(id -> !id.isBlank())
                .orElse(UUID.randomUUID().toString());

        // 3) Put the traceId into MDC so that logs contain it
        MDC.put("traceId", traceId);

        // 4) Forward the same traceId to downstream services by adding/overwriting the header
        ServerHttpRequest mutatedRequest = exchange.getRequest()
                .mutate()
                .header(TRACE_ID_HEADER, traceId)
                .build();

        log.info("Incoming request => traceId: {}", traceId);

        // 5) Pass the mutated request along the filter chain
        return chain.filter(exchange.mutate().request(mutatedRequest).build())
                    .doFinally(signalType -> {
                        // Remove from MDC after the request completes to avoid any cross-thread contamination
                        MDC.remove("traceId");
                    });
    }

    @Override
    public int getOrder() {
        // Higher priority (lower value) ensures the filter runs early
        // so subsequent logging statements have the traceId in MDC.
        return -2;
    }
}

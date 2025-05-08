package com.example.auth.logging;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

/**
 * Filter that ensures each request has a trace ID in MDC.
 * Picks up X-Trace-ID from the request or generates a new one.
 */
@Slf4j
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class TraceIdFilter implements Filter {

    private static final String TRACE_ID_HEADER = "X-Trace-ID";

    @Override
    public void doFilter(
            jakarta.servlet.ServletRequest servletRequest,
            jakarta.servlet.ServletResponse servletResponse,
            FilterChain chain
    ) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        try {
            String traceId = request.getHeader(TRACE_ID_HEADER);
            log.info("Auth-Service => traceId from request: {}", traceId);
            if (traceId == null || traceId.isBlank()) {
                log.info("Auth-Service => traceId not found in request, generating a new one");
                traceId = UUID.randomUUID().toString();
            }

            MDC.put("traceId", traceId);
            response.setHeader(TRACE_ID_HEADER, traceId);

            log.info("Auth-Service => traceId set to: {}", traceId);

            chain.doFilter(servletRequest, servletResponse);

        } finally {
            MDC.remove("traceId");
        }
    }
}

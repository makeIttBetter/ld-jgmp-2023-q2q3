package com.example.auth.logging;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.context.annotation.Configuration;

/**
 * A Feign interceptor to forward the X-Trace-ID
 * from MDC into every Feign request header.
 */
@Slf4j
@Configuration
public class TraceIdFeignInterceptor implements RequestInterceptor {

    private static final String TRACE_ID_HEADER = "X-Trace-ID";

    @Override
    public void apply(RequestTemplate template) {
        // Grab the traceId from the MDC
        String traceId = MDC.get("traceId");
        if (traceId != null && !traceId.isBlank()) {
            // Add or overwrite the header in the outbound Feign request
            template.header(TRACE_ID_HEADER, traceId);
            log.debug("Adding X-Trace-ID header to Feign request: {}", traceId);
        }
    }
}

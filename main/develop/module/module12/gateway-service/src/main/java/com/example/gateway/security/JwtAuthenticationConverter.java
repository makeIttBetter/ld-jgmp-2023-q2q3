// src/main/java/com/example/gateway/security/JwtAuthenticationConverter.java

package com.example.gateway.security;

import org.springframework.http.HttpCookie;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Extracts the JWT token from the HttpOnly cookie and creates an Authentication object.
 */
@Component
public class JwtAuthenticationConverter implements ServerAuthenticationConverter {

    private static final String AUTH_TOKEN_COOKIE = "AuthToken";

    @Override
    public Mono<Authentication> convert(ServerWebExchange exchange) {
        ServerHttpRequest request = exchange.getRequest();
        List<HttpCookie> cookies = request.getCookies().get(AUTH_TOKEN_COOKIE);

        if (cookies == null || cookies.isEmpty()) {
            return Mono.empty();
        }

        String token = cookies.get(0).getValue();
        return Mono.just(new UsernamePasswordAuthenticationToken(token, token));
    }
}

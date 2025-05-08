// CHANGES: Return user info (with roles) instead of just Boolean
// So create a DTO inside gateway too, matching what Auth Service returns.

package com.example.gateway.service;

import com.example.gateway.dto.TokenVerificationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final WebClient.Builder webClientBuilder;

    /**
     * Verifies the JWT token with the AUTH-SERVICE, returns user info if valid.
     *
     * @param token JWT token to verify
     * @return Mono<TokenVerificationResponse> with user validity and roles
     */
    public Mono<TokenVerificationResponse> verifyToken(String token) {
        log.info("Verifying token with Auth Service...");
        return webClientBuilder.build()
                .get()
                .uri("lb://AUTH-SERVICE/auth/token/verify?token={token}", token)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response -> {
                    log.warn("Token invalid or expired");
                    return Mono.empty(); // Or you could return Mono.error(new RuntimeException(...))
                })
                .bodyToMono(TokenVerificationResponse.class)
                .onErrorResume(e -> {
                    log.warn("Auth Service check failed: {}", e.getMessage(), e);
                    return Mono.just(new TokenVerificationResponse(false, null, null));
                });
    }
}

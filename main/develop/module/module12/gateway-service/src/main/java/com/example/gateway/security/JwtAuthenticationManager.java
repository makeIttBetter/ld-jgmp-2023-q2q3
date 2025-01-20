package com.example.gateway.security;

import com.example.gateway.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationManager implements ReactiveAuthenticationManager {

    private final AuthService authService;

    /**
     * Verifies the JWT token with the Auth Service and extracts authorities.
     */
    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        log.info("Authenticating user in Gateway...");
        String token = authentication.getCredentials().toString();

        // 1) Call Auth Service to verify token and get user info
        return authService.verifyToken(token)
                .flatMap(response -> {
                    if (response.isValid()) {
                        log.info("User authenticated successfully by Auth Service");

                        // Convert roles from String to SimpleGrantedAuthority
                        List<SimpleGrantedAuthority> authorities = response.getRoles().stream()
                                .map(SimpleGrantedAuthority::new)
                                .toList();

                        // Create a new trusted UsernamePasswordAuthenticationToken
                        Authentication authToken =
                                new UsernamePasswordAuthenticationToken(
                                        response.getUsername(),
                                        token,
                                        authorities
                                );

                        return Mono.just(authToken);
                    } else {
                        log.warn("Invalid or expired token");
                        return Mono.error(new BadCredentialsException("Invalid Token"));
                    }
                })
                .onErrorResume(e -> {
                    log.warn("Authentication failed: {}", e.getMessage());
                    return Mono.error(new BadCredentialsException("Authentication failed"));
                });
    }
}

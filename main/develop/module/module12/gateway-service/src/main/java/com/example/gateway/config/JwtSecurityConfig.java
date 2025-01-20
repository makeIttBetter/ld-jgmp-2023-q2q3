package com.example.gateway.config;

import com.example.gateway.config.props.SecurityProperties;
import com.example.gateway.filter.TraceIdGlobalFilter;
import com.example.gateway.security.JwtAuthenticationConverter;
import com.example.gateway.security.JwtAuthenticationManager;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.util.matcher.OrServerWebExchangeMatcher;
import org.springframework.security.web.server.util.matcher.PathPatternParserServerWebExchangeMatcher;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatcher;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;

import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * Reactive security configuration for API endpoints.
 * Defines authentication for secured routes while permitting public endpoints.
 */
@Slf4j
@Configuration
@EnableWebFluxSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class JwtSecurityConfig {

    private final SecurityProperties securityProperties;
    private final JwtAuthenticationConverter jwtAuthenticationConverter;
    private final JwtAuthenticationManager jwtAuthenticationManager;
    private final TraceIdGlobalFilter traceIdGlobalFilter;

    private static List<String> PUBLIC_PATHS;
    private static List<String> SECURED_PATHS;


    @PostConstruct
    public void init() {
        log.debug("Public paths: {}", securityProperties.getPublicPaths());
        PUBLIC_PATHS = securityProperties.getPublicPaths();
        log.debug("Secured paths: {}", securityProperties.getSecuredPaths());
        SECURED_PATHS = securityProperties.getSecuredPaths();
    }

    /**
     * Configures the public SecurityWebFilterChain which permits all requests to public endpoints.
     *
     * @param http ServerHttpSecurity instance
     * @return Configured SecurityWebFilterChain
     */
    @Bean
    @Order(1)
    public SecurityWebFilterChain publicSecurityWebFilterChain(ServerHttpSecurity http) {
        http
                .securityMatcher(createPublicPathsMatcher())
                .authorizeExchange(exchange -> exchange
                        .anyExchange().permitAll()
                )
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .cors(withDefaults());

        log.debug("Configured Public SecurityWebFilterChain for paths: {}", PUBLIC_PATHS);
        return http.build();
    }

    /**
     * Configures the secured SecurityWebFilterChain which requires authentication for all other requests.
     *
     * @param http ServerHttpSecurity instance
     * @return Configured SecurityWebFilterChain
     */
    @Bean
    @Order(2)
    public SecurityWebFilterChain securedSecurityWebFilterChain(ServerHttpSecurity http) {
        AuthenticationWebFilter jwtAuthenticationFilter = createJwtAuthenticationFilter();

        http
                .securityMatcher(createSecuredPathsMatcher())
                .authorizeExchange(exchange -> exchange
                        .anyExchange().authenticated()
                )
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .cors(withDefaults())
                .addFilterAt(jwtAuthenticationFilter, SecurityWebFiltersOrder.AUTHENTICATION);

        log.debug("Configured Secured SecurityWebFilterChain for all other paths: {}", SECURED_PATHS);
        return http.build();
    }

    /**
     * Creates a matcher for public paths.
     *
     * @return ServerWebExchangeMatcher for public paths
     */
    private ServerWebExchangeMatcher createPublicPathsMatcher() {
        return new OrServerWebExchangeMatcher(
                PUBLIC_PATHS.stream()
                        .map(PathPatternParserServerWebExchangeMatcher::new)
                        .toArray(PathPatternParserServerWebExchangeMatcher[]::new)
        );
    }

    /**
     * Creates a matcher for secured paths (all paths).
     *
     * @return ServerWebExchangeMatcher for secured paths
     */
    private ServerWebExchangeMatcher createSecuredPathsMatcher() {
        return ServerWebExchangeMatchers.pathMatchers(SECURED_PATHS.toArray(String[]::new));
    }

    /**
     * Creates and configures the JWT AuthenticationWebFilter.
     *
     * @return Configured AuthenticationWebFilter
     */
    private AuthenticationWebFilter createJwtAuthenticationFilter() {
        AuthenticationWebFilter jwtAuthFilter = new AuthenticationWebFilter(jwtAuthenticationManager);
        jwtAuthFilter.setServerAuthenticationConverter(jwtAuthenticationConverter);
        jwtAuthFilter.setRequiresAuthenticationMatcher(createSecuredPathsMatcher());
        jwtAuthFilter.setAuthenticationFailureHandler((webFilterExchange, exception) -> {
            log.warn("Authentication failed: {}", exception.getMessage());
            webFilterExchange.getExchange().getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return webFilterExchange.getExchange().getResponse().setComplete();
        });

        log.debug("JWT AuthenticationWebFilter configured");
        return jwtAuthFilter;
    }
}

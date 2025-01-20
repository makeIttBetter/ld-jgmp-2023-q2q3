
package com.develop.springboot.config;

import com.develop.springboot.filter.JwtCookieAuthenticationFilter;
import com.develop.springboot.filter.entrypoint.JwtAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * Main security configuration for API endpoints.
 * Defines authentication for /api/** routes.
 */
@Slf4j
@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
@Order(3)
public class JwtSecurityConfig {

    private final JwtCookieAuthenticationFilter jwtCookieAuthenticationFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    @Order
    public SecurityFilterChain jwtTestSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/appi/**")
                .cors(withDefaults()) // Enable CORS support
                // Disable CSRF since we rely on JWT + HttpOnly cookies, and use SameSite=Strict
                .csrf(AbstractHttpConfigurer::disable)
                // Set proper permissions
                .authorizeHttpRequests(authorize -> authorize
                                .requestMatchers("/appi/auth/**").permitAll()
//                        .requestMatchers("/appi/auth/userinfo").authenticated()
                                .anyRequest().authenticated()
                )
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                // Apply our custom JWT filter
                .addFilterBefore(jwtCookieAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // Additional chain if needed for other endpoints (e.g., web) can be configured similarly.
}



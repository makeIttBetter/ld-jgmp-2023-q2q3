package com.develop.springboot.config;

import com.develop.springboot.filter.JwtRequestFilter;
import com.develop.springboot.service.security.JwtAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * This class configures security for API endpoints.
 * <p> It is annotated with {@link Configuration} to indicate that it is a Spring configuration class.
 * <p> It is annotated with {@link Order} to indicate that it should be processed after {@link WebSecurityConfig}.
 */
@Configuration
@Order(2)
public class ApiSecurityConfig {

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    /**
     * This method configures security logic for API endpoints.
     * <p> It is annotated with {@link Bean} to indicate that it is a Spring bean factory method.
     *
     * @param http - {@link HttpSecurity} object to configure security logic
     * @return {@link SecurityFilterChain} configured security filter chain for API endpoints
     * @throws Exception - if an error occurs while configuring security logic
     * @see HttpSecurity - Spring Security class that allows configuring web based security for specific http requests
     */
    @Bean
    public SecurityFilterChain apiSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/api/**")
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/authenticate").permitAll()
                        .requestMatchers("/api/tickets**").hasRole("ADMIN")
                        .requestMatchers("/api/users**").hasRole("USER")
                        .requestMatchers("/api/events**").permitAll()
                        .anyRequest().authenticated()
                )
                .exceptionHandling(handler -> handler
                        .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                )
                .sessionManagement(manager -> manager
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
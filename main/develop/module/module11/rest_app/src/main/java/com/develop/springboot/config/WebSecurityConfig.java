package com.develop.springboot.config;

import com.develop.springboot.service.security.CustomOAuth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * This class configures security for web endpoints.
 * <p> It is annotated with {@link Configuration} to indicate that it is a Spring configuration class.
 * <p> It is annotated with {@link EnableWebSecurity} to indicate that it is a Spring web security class.
 * <p> It is annotated with {@link EnableMethodSecurity} to indicate that it is a Spring method security class.
 * <p> It is annotated with {@link Order} to indicate that it should be processed before {@link ApiSecurityConfig}.
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@Order(1)
public class WebSecurityConfig {

    @Autowired
    private CustomOAuth2UserService customOAuth2UserService;

    /**
     * This method configures security logic for web endpoints.
     * <p> It is annotated with {@link Bean} to indicate that it is a Spring bean factory method.
     *
     * @param http - {@link HttpSecurity} object to configure security logic
     * @return {@link SecurityFilterChain} configured security filter chain for web endpoints
     * @throws Exception - if an error occurs while configuring security logic
     * @see HttpSecurity - Spring Security class that allows configuring web based security for specific http requests
     */
    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/secured/all").authenticated()
                        .requestMatchers("/secured/admin").hasRole("ADMIN")
                        .requestMatchers("/secured/user").hasRole("USER")
                        .anyRequest().anonymous()
                )
                .oauth2Login(oauth2 -> oauth2
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(customOAuth2UserService)
                        )
                )
                .formLogin(withDefaults());
        return http.build();
    }

}

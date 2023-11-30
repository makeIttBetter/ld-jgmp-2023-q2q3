package com.develop.springboot.service.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * JwtAuthenticationEntryPoint class is used to return a 401 unauthorized error to clients that try to access a protected resource without proper authentication.
 * It implements AuthenticationEntryPoint interface.
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    /**
     * Commence method is called whenever an exception is thrown due to an unauthenticated user trying to access a resource that requires authentication.
     * In this case, the method simply returns a 401 Unauthorized response to the client.
     *
     * @param request       the HTTP request
     * @param response      the HTTP response
     * @param authException the authentication exception
     * @throws IOException in case of an I/O exception
     */
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        // This is where you can place custom behavior on authentication errors. For example:
        // If the user tried to access a secured REST resource while not being authenticated
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }

}

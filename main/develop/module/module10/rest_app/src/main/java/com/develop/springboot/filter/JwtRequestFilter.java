package com.develop.springboot.filter;

import com.develop.springboot.service.security.UserDetailsServiceImpl;
import com.develop.springboot.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * JwtRequestFilter class is a filter for JWT requests.
 * It provides methods for filtering requests and extracting JWT token.
 * It is used for authenticating users using JWT.
 * <p> It extends OncePerRequestFilter class, which means it will be executed once per each request.
 */
@Slf4j
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * Filters incoming requests to extract and validate JWT tokens.
     * It authenticates the user if a valid JWT token is present in the Authorization header.
     * The filter is applied to every HTTP request but performs authentication only for requests with JWT tokens.
     *
     * @param request     the HTTP request
     * @param response    the HTTP response
     * @param filterChain the filter chain
     * @throws ServletException in case of a servlet exception
     * @throws IOException      in case of an I/O exception
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            username = jwtUtil.extractUsername(jwt);
            log.info("JWT Token parsed, user: {}", username);
        } else {
            log.debug("No Authorization header found or Bearer prefix missing");
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            log.info("Authenticating user: {}", username);
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

            if (jwtUtil.validateToken(jwt)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                log.info("User {} authenticated successfully using JWT", username);
            } else {
                log.warn("JWT Token validation failed for user: {}", username);
            }
        }

        filterChain.doFilter(request, response);
        log.info("Filter chain proceeded for user: {}", username);
    }
}

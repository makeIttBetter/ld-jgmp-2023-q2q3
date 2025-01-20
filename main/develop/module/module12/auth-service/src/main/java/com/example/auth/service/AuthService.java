package com.example.auth.service;

import com.example.auth.dto.RoleDto;
import com.example.auth.dto.TokenVerificationResponse;
import com.example.auth.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final UserDetailsService customUserDetailsService;

    /**
     * Attempts to authenticate the user by username OR email + password
     */
    public String authenticate(String identifier, String rawPassword) {
        // Let Spring Security handle the logic via custom user details
        log.info("Authenticating user: {}", identifier);
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(identifier, rawPassword)
            );
            log.info("User authenticated: {}", identifier);
            return identifier;
        } catch (Exception e) {
            log.error("Failed to authenticate user: {}", identifier);
            return null; // indicates invalid credentials
        }
    }

    public String generateToken(String identifier) {
        log.debug("Generating token for user: {}", identifier);
        // You can load user details again if needed:
        // UserDetails userDetails = customUserDetailsService.loadUserByUsername(identifier);
        // or just store the identifier in the token
        return jwtUtil.createToken(identifier);
    }

    public boolean isTokenValid(String token) {
        log.debug("Validating token");
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(jwtUtil.extractUsername(token));
        return jwtUtil.validateToken(token, userDetails);
    }

    public TokenVerificationResponse verifyAndGetUserInfo(String token) {
        // 1) Check if the token is valid
        if (!isTokenValid(token)) {
            return new TokenVerificationResponse(false, null, null);
        }

        // 2) Extract username from token
        String username = jwtUtil.extractUsername(token);

        // 3) Query the database (through the userService) to get user roles
        //    Our userService.findByIdentifier(...) fetches the user with roles included
        var userEntity = userService.findByIdentifier(username);
        if (userEntity == null) {
            return new TokenVerificationResponse(false, null, null);
        }

        // Convert each RoleEntity to a string like "ROLE_USER"
        List<String> roleList = userEntity.getRoles().stream()
                .map(RoleDto::getConstantCode)
                .toList();

        // 4) If everything is good, return the user info
        return new TokenVerificationResponse(true, username, roleList);
    }


}

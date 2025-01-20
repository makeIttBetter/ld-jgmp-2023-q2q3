// File: C:/ws/projects/low-code-ai-tool/auth-service/src/main/java/com/example/auth/controller/AuthController.java

package com.example.auth.controller;

import com.example.auth.client.UserServiceClient;
import com.example.auth.dto.LoginRequest;
import com.example.auth.dto.SignupRequest;
import com.example.auth.dto.TokenVerificationResponse;
import com.example.auth.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserServiceClient userServiceClient;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request, HttpServletResponse response) {
        log.info("Call: /auth/login. Logging in user: {}", request.getUsername());
        String username = authService.authenticate(request.getUsername(), request.getPassword());
        if (username != null) {
            String jwt = authService.generateToken(username);
            // Set JWT as an HttpOnly, Secure, SameSite=Strict cookie
            response.addHeader(
                    "Set-Cookie",
                    "AuthToken=" + jwt
                            + "; HttpOnly; Secure; SameSite=Strict; Path=/; Max-Age=3600"
            );
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest) {
        signupRequest.setUsername(signupRequest.getEmail());
        log.info("Call: /auth/signup. Creating new user: {}", signupRequest.getUsername());
        try {
            // We ask the UserService to create a new record
            userServiceClient.createUser(signupRequest);
            return ResponseEntity.ok("User registered successfully");
        } catch (Exception e) {
            log.error("Signup failed: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Existing token verification endpoint
    @GetMapping("/token/verify")
    public ResponseEntity<TokenVerificationResponse> verifyToken(@RequestParam("token") String token) {
        log.info("Call: /auth/token/verify. Verifying token: {}", token);
        TokenVerificationResponse result = authService.verifyAndGetUserInfo(token);
        if (result.isValid()) {
            log.info("Token is valid for user: {}", result.getUsername());
            return ResponseEntity.ok(result);
        } else {
            log.warn("Invalid or expired token");
            return ResponseEntity.status(401).body(new TokenVerificationResponse(false, null, null));
        }
    }

    @PostMapping("/token")
    public ResponseEntity<?> generateToken(@RequestBody String username) {
        log.info("Call: /auth/token. Generating token for user: {}", username);
        String newJwt = authService.generateToken(username);
        return ResponseEntity.ok(newJwt);
    }
}

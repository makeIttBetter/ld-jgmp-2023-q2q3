package com.develop.springboot.controller;

import com.develop.springboot.dto.LoginRequest;
import com.develop.springboot.dto.UserInfoResponse;
import com.develop.springboot.service.UserServiceImpl;
import com.develop.springboot.util.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Handles user authentication endpoints.
 */
//@CrossOrigin(origins = {"http://localhost:3002", "https://localhost:3002"},
//        allowCredentials = "true")
@RestController
@RequestMapping("/appi/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserServiceImpl userService;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request, HttpServletResponse response) {
        Optional<String> usernameOpt = userService.authenticate(request.getUsername(), request.getPassword());
        if (usernameOpt.isPresent()) {
            String jwt = jwtUtil.generateToken(usernameOpt.get());

            // Set JWT as HttpOnly, Secure, SameSite=Strict cookie
            // "Secure" requires HTTPS, for development use HTTPS or remove secure in dev mode
            response.addHeader("Set-Cookie", "AuthToken=" + jwt + "; HttpOnly; SameSite=Strict; Path=/; Max-Age=3600");
//            response.addHeader("Set-Cookie", "AuthToken=" + jwt + "; HttpOnly; Secure; SameSite=Strict; Path=/; Max-Age=3600");
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

    @GetMapping("/userinfo")
    public ResponseEntity<UserInfoResponse> userInfo(@RequestAttribute("username") String username) {
        return ResponseEntity.ok(new UserInfoResponse(username, "Your protected profile data"));
    }
}

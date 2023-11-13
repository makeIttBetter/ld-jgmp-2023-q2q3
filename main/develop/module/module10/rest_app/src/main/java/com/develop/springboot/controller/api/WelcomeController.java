package com.develop.springboot.controller.api;

import com.develop.springboot.dto.AuthRequest;
import com.develop.springboot.dto.AuthenticationResponse;
import com.develop.springboot.service.security.UserDetailsServiceImpl;
import com.develop.springboot.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
public class WelcomeController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> generateToken(@RequestBody AuthRequest authRequest) {
        try {
            log.info("Authentication request for user: {}", authRequest.getUsername());
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
            log.info("Authentication successful for user: {}", authRequest.getUsername());
        } catch (Exception ex) {
            log.error("Authentication failed for user: {}. Error: {}", authRequest.getUsername(), ex.getMessage());
            throw new RuntimeException("Invalid username/password");  // Changed to RuntimeException to avoid having to declare throws Exception
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
        String jwt = jwtUtil.generateToken(userDetails.getUsername());
        log.info("JWT token generated for user: {}", authRequest.getUsername());

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}

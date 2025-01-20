package com.example.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Response returned to the Gateway when verifying a token:
 * includes username and roles.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenVerificationResponse {
    private boolean valid;
    private String username;
    private List<String> roles;
}

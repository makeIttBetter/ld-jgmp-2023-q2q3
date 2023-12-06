package com.develop.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for JWT authentication response.
 *
 * @see com.develop.springboot.controller.api.WelcomeController
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private String jwt;
}
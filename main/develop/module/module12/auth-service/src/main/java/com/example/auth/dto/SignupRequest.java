// File: C:/ws/projects/low-code-ai-tool/auth-service/src/main/java/com/example/auth/dto/SignupRequest.java

package com.example.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Basic signup payload
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest {
    private String username;
    private String email;
    private String password;
}

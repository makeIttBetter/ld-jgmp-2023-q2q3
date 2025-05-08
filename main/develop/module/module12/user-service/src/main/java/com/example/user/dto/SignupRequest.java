// File: C:/ws/projects/low-code-ai-tool/user-service/src/main/java/com/example/user/dto/SignupRequest.java

package com.example.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest {
    private String username;
    private String email;
    private String password;
}

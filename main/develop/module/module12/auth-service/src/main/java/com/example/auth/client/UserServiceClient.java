// File: C:/ws/projects/low-code-ai-tool/auth-service/src/main/java/com/example/auth/client/UserServiceClient.java

package com.example.auth.client;

import com.example.auth.dto.SignupRequest;
import com.example.auth.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "USER-SERVICE")
public interface UserServiceClient {

    @GetMapping("/users/identifier")
    ResponseEntity<UserDto> findByIdentifier(@RequestParam("identifier") String identifier);

    // NEW method to create a user
    @PostMapping("/users/signup")
    ResponseEntity<UserDto> createUser(@RequestBody SignupRequest signupRequest);
}

package com.example.auth.service;

import com.example.auth.client.UserServiceClient;
import com.example.auth.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserServiceClient userServiceClient;

    /**
     * Find user by identifier. (username or email)
     *
     * @param identifier the identifier (username or email)
     * @return the user dto
     */
    public UserDto findByIdentifier(String identifier) {
        try {
            log.debug("Finding user by identifier: {}", identifier);

            ResponseEntity<UserDto> response = userServiceClient.findByIdentifier(identifier);

            if (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null) {
                throw new UsernameNotFoundException("User not found with username or email: " + identifier);
            }

            return response.getBody();
        } catch (Exception e) {
            log.error("Error occurred while trying to find user by identifier: {}", identifier, e);
            throw new RuntimeException("Error occurred while trying to find user by identifier: " + identifier, e);
        }
    }

}

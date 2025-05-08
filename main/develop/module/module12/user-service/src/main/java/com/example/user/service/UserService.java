// File: C:/ws/projects/low-code-ai-tool/user-service/src/main/java/com/example/user/service/UserService.java

package com.example.user.service;

import com.example.user.dto.SignupRequest;
import com.example.user.entity.RoleEntity;
import com.example.user.entity.UserEntity;
import com.example.user.repository.RoleRepository;
import com.example.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    // For the standard CrudController
    public UserEntity save(UserEntity user) {
        log.info("Saving user with username: {}", user.getUsername());
        return userRepository.save(user);
    }

    public UserEntity findById(String id) {
        log.info("Finding user by ID: {}", id);
        return userRepository.findById(id).orElse(null);
    }

    public List<UserEntity> findAll() {
        log.info("Finding all users.");
        return userRepository.findAll();
    }

    public UserEntity update(String id, UserEntity updatedUser) {
        log.info("Updating user with ID: {}", id);
        return userRepository.findById(id)
                .map(existing -> {
                    existing.setUsername(updatedUser.getUsername());
                    existing.setEmail(updatedUser.getEmail());
                    // handle password, roles, etc. as needed
                    return userRepository.save(existing);
                })
                .orElse(null);
    }

    public boolean delete(String id) {
        log.info("Deleting user with ID: {}", id);
        return userRepository.findById(id)
                .map(user -> {
                    userRepository.delete(user);
                    return true;
                }).orElse(false);
    }

    public UserEntity findByIdentifier(String identifier) {
        log.info("Finding user by email or username: {}", identifier);
        return new UserEntity("alice", "alice@gmail.com",
                "$2a$10$R1spj5w.4kVdU3Naylqj2.jlEVZb3Eyc9sdrBcc4UGxwTp7LobB5y", Set.of());
//        return userRepository.findByEmailOrUsername(identifier);
    }


    public UserEntity registerNewUser(SignupRequest signupRequest) {
        log.info("Registering new user with username: {}", signupRequest.getUsername());
        // Check if user already exists
        UserEntity existingUser = findByIdentifier(signupRequest.getUsername());
        if (existingUser != null) {
            log.error("User with this username or email already exists.");
            throw new RuntimeException("User with this username or email already exists.");
        }
        // Create new user
        UserEntity user = new UserEntity();
        user.setUsername(signupRequest.getUsername());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));

        // Example of adding default "ROLE_USER"
        RoleEntity defaultRole = roleRepository.findByConstantCode("USER");
        if (defaultRole != null) {
            user.getRoles().add(defaultRole);
        }
        return userRepository.save(user);
    }
}

// File: C:/ws/projects/low-code-ai-tool/user-service/src/main/java/com/example/user/controller/UserCrudController.java

package com.example.user.controller;

import com.example.user.dto.SignupRequest;
import com.example.user.dto.UserInfoResponse;
import com.example.user.entity.UserEntity;
import com.example.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserCrudController implements CrudController<UserEntity, String> {

    private final UserService userService;

    @Override
    @PostMapping
    public ResponseEntity<UserEntity> create(@RequestBody UserEntity entity) {
        log.info("Call: POST /users. Creating user with username: {}", entity.getUsername());
        UserEntity newUser = userService.save(entity);
        return ResponseEntity.ok(newUser);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getById(@PathVariable String id) {
        log.info("Call: GET /users/{}. Finding user by ID.", id);
        UserEntity user = userService.findById(id);
        return (user != null)
                ? ResponseEntity.ok(user)
                : ResponseEntity.notFound().build();
    }

    @Override
    @GetMapping
    public ResponseEntity<List<UserEntity>> getAll() {
        log.info("Call: GET /users. Finding all users.");
        return ResponseEntity.ok(userService.findAll());
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<UserEntity> update(@PathVariable String id, @RequestBody UserEntity updatedEntity) {
        log.info("Call: PUT /users/{}. Updating user.", id);
        UserEntity user = userService.update(id, updatedEntity);
        return (user != null)
                ? ResponseEntity.ok(user)
                : ResponseEntity.notFound().build();
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        log.info("Call: DELETE /users/{}. Deleting user.", id);
        boolean deleted = userService.delete(id);
        return deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }


    @GetMapping("/identifier")
    public ResponseEntity<UserEntity> findByUsernameOrEmail(@RequestParam("identifier") String identifier) {
        log.info("Call: /users/identifier. Finding user by identifier: {}", identifier);
        UserEntity user = userService.findByIdentifier(identifier);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    // TODO: Remove this endpoint
    @GetMapping("/userinfo")
    public ResponseEntity<UserInfoResponse> getUserInfo(@RequestParam("username") String username) {
        log.info("Call: /users/userinfo. Getting user info for: {}", username);
        return ResponseEntity.ok(new UserInfoResponse(username, "Your protected profile data"));
    }


    @Operation(summary = "Create a new user from signup request",
            description = "Creates a new user record with hashed password.")
    @PostMapping("/signup")
    public ResponseEntity<UserEntity> createUserViaSignup(@RequestBody SignupRequest signupRequest) {
        log.info("Call: /users/signup. Creating user with username: {}", signupRequest.getUsername());
        UserEntity newUser = userService.registerNewUser(signupRequest);
        return ResponseEntity.ok(newUser);
    }
}

package com.develop.springboot.service;

import com.develop.springboot.model.entities.User;
import com.develop.springboot.model.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * UserServiceImpl class is a service for customizing user service.
 * It provides methods for saving, finding and deleting users.
 * <p> It implements Service interface, which means it will be used for saving, finding and deleting users.
 */
@Slf4j
@org.springframework.stereotype.Service
public class UserServiceImpl implements Service<User, UUID> {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void save(User user) {
        log.info("Attempting to save user: {}", user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        log.info("User saved successfully: {}", user);
    }

    @Override
    public Optional<User> findById(UUID id) {
        log.info("Searching for user by id: {}", id);
        Optional<User> user = userRepository.findById(id);
        log.info("User search result for id {}: {}", id, user);
        return user;
    }

    @Override
    public List<User> findAll() {
        log.info("Retrieving all users");
        List<User> users = userRepository.findAll();
        log.info("Found {} users", users.size());
        return users;
    }

    @Override
    public void deleteById(UUID id) {
        log.info("Deleting user by id: {}", id);
        userRepository.deleteById(id);
        log.info("User with id {} deleted successfully", id);
    }
}

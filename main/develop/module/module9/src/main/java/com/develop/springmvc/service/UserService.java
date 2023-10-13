package com.develop.springmvc.service;

import com.develop.springmvc.model.dao.UserDao;
import com.develop.springmvc.model.entities.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@org.springframework.stereotype.Service
public class UserService implements Service<User, UUID> {

    @Autowired
    private UserDao userDao;

    @Override
    public void save(User user) {
        log.info("Attempting to save user: {}", user);
        userDao.save(user);
        log.info("User saved successfully: {}", user);
    }

    @Override
    public Optional<User> findById(UUID id) {
        log.info("Searching for user by id: {}", id);
        Optional<User> user = userDao.findById(id);
        log.info("User search result for id {}: {}", id, user);
        return user;
    }

    @Override
    public List<User> findAll() {
        log.info("Retrieving all users");
        List<User> users = userDao.findAll();
        log.info("Found {} users", users.size());
        return users;
    }

    @Override
    public void deleteById(UUID id) {
        log.info("Deleting user by id: {}", id);
        userDao.deleteById(id);
        log.info("User with id {} deleted successfully", id);
    }
}

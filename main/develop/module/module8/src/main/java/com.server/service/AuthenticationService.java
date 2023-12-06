package com.server.service;

import com.server.model.User;
import com.server.util.PasswordService;

import java.util.Map;

public class AuthenticationService {
    private final Map<String, User> users;
    private final PasswordService passwordService;
    private final SessionManager sessionManager;

    public AuthenticationService(Map<String, User> users, PasswordService passwordService, SessionManager sessionManager) {
        this.users = users;
        this.passwordService = passwordService;
        this.sessionManager = sessionManager;
    }

    public User authenticate(String username, String password) {
        User user = users.get(username);
        if (user != null && passwordService.checkPassword(password, user.getPassword())) {
            return user;
        }
        return null;
    }

    public boolean logOut(User user) {
        System.out.println("Logging out...");
        sessionManager.invalidateSession(user);
        return true;
    }
}

package com.server;

import com.server.model.User;
import com.server.model.Role;
import com.server.util.PasswordService;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public class TestDataLoader {
    private static final PasswordService passwordService = new PasswordService();

    public static Map<String, User> loadUsers() {
        Map<String, User> users = new HashMap<>();
        users.put("admin", new User("admin", passwordService.hashPassword("adminpass"), Role.ADMIN));
        users.put("user", new User("user", passwordService.hashPassword("userpass"), Role.USER));
        return users;
    }
}


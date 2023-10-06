package com.server.model;

import lombok.Data;

/**
 * User model.
 */
@Data
public class User {
    private String username;
    private String password;
    private Role role;

    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Constructors, getters, and setters

}

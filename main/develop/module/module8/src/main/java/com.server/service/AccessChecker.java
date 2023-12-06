package com.server.service;


import com.server.config.ServerConfig;
import com.server.model.User;

/**
 * Checks if a user may access a command.
 */
public class AccessChecker {
    private static AccessChecker instance;
    private final ServerConfig config = ServerConfig.getInstance();

    private AccessChecker() {
        // Initialization
    }

    /**
     * Singleton pattern.
     *
     * @return AccessChecker instance
     */
    public static AccessChecker getInstance() {
        if (instance == null) {
            instance = new AccessChecker();
        }
        return instance;
    }

    /**
     * Checks if user can execute command.
     *
     * @param user User to check access for
     * @param command Command to check access to
     * @return true if user can execute command, false otherwise
     */
    public boolean mayAccess(User user, String command) {
        return config.mayAccess(user, command);
    }
}


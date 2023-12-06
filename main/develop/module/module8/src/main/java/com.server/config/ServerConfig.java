package com.server.config;

import com.server.model.Role;
import com.server.model.User;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * Singleton class that loads configurations from properties files and provides access to them.
 * It loads roles from roles.properties and all other necessary configurations from application.properties.
 * Provides methods to get all these properties.
 */
public class ServerConfig {
    private static ServerConfig instance;
    private final Map<String, Set<String>> roleCommandMap;
    private final Properties properties;

    private static final String ROLES_PROPERTIES = "roles.properties";
    private static final String APPLICATION_PROPERTIES = "application.properties";
    private static final String SESSION_TIMEOUT_SECONDS = "session.timeout.seconds";

    /**
     * Private constructor to prevent instantiation.
     * Loads roles.properties and application.properties.
     */
    private ServerConfig() {
        roleCommandMap = new HashMap<>();
        properties = new Properties();
        loadRolesConfigurations();
        loadApplicationProperties();
    }

    /**
     * Singleton pattern.
     *
     * @return ServerConfig instance
     */
    public static synchronized ServerConfig getInstance() {
        if (instance == null) {
            instance = new ServerConfig();
        }
        return instance;
    }

    /**
     * Loads roles.properties file and populates roleCommandMap.
     * <p> It is used to load only to load roles and commands that they can execute from properties. </p>
     */
    private void loadRolesConfigurations() {
        Properties prop = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(ROLES_PROPERTIES)) {
            prop.load(input);
            for (String role : prop.stringPropertyNames()) {
                Set<String> commands = new HashSet<>(Arrays.asList(prop.getProperty(role).split(",")));
                roleCommandMap.put(role, commands);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Failed to load roles.properties");
        }
    }

    /**
     * Loads application.properties file.
     * <p> It is used to load all other configurations from properties. </p>
     */
    private void loadApplicationProperties() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(APPLICATION_PROPERTIES)) {
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Failed to load application.properties");
        }
    }

    /**
     * Checks if user can execute command.
     *
     * @param user    User to check access for
     * @param command Command to check access to
     * @return true if user can execute command, false otherwise
     */
    public boolean mayAccess(User user, String command) {
        return roleCommandMap.getOrDefault(user.getRole().toString(), Collections.emptySet()).contains(command);
    }

    /**
     * Finds representation of user's access level.
     *
     * @param user User to get access level for
     * @return Commands that user can execute, separated by comma.
     */
    public String getAccessLevel(User user) {
        return roleCommandMap.getOrDefault(user.getRole().toString(), Collections.emptySet()).toString();
    }

    /**
     * Gets all available commands for role.
     *
     * @param role Role to get commands for
     * @return Set of commands that role can execute
     */
    public Set<String> getAvailableCommandsForRole(Role role) {
        return roleCommandMap.getOrDefault(role.toString(), Collections.emptySet());
    }


    /**
     * Gets the session timeout value configured in application.properties.
     *
     * @return Session timeout in minutes
     */
    public long getSessionTimeout() {
        return Long.parseLong(properties.getProperty(SESSION_TIMEOUT_SECONDS));
    }
}

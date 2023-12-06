package com.server;

import com.server.config.ServerConfig;
import com.server.model.User;
import com.server.service.AuthenticationService;
import com.server.service.CommandExecutorService;
import com.server.service.SessionManager;

import java.util.Scanner;
import java.util.Set;

public class CLI {
    private final AuthenticationService authService;
    private final CommandExecutorService commandExecutorService;
    private final Scanner scanner;
    private final SessionManager sessionManager;

    public CLI(AuthenticationService authService, CommandExecutorService commandExecutorService, SessionManager sessionManager) {
        this.authService = authService;
        this.commandExecutorService = commandExecutorService;
        this.sessionManager = sessionManager;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        String username, password;
        while (true) {
            System.out.print("Enter username (or type 'exit' to quit): ");
            username = scanner.nextLine();
            if ("exit".equalsIgnoreCase(username)) {
                System.out.println("Goodbye!");
                break;
            }
            System.out.print("Enter password: ");
            password = scanner.nextLine();

            User user = authService.authenticate(username, password);
            if (user != null) {
                System.out.println("Login successful.");
                handleUserInput(user);
            } else {
                System.out.println("Invalid credentials");
            }
        }
    }

    private void handleUserInput(User user) {
        String commandInput;
        do {
            Set<String> availableCommands = ServerConfig.getInstance().getAvailableCommandsForRole(user.getRole());
            System.out.println("Available commands: " + availableCommands);
            System.out.print("> ");
            commandInput = scanner.nextLine();
            commandExecutorService.executeCommand(commandInput, user);
        } while (sessionManager.getActiveSession(user) != null);
    }
}

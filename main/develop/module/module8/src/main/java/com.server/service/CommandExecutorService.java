package com.server.service;

import com.server.command.Command;
import com.server.exception.InsufficientRightsException;
import com.server.exception.SessionExpiredException;
import com.server.model.Session;
import com.server.model.User;

import java.util.Map;

public class CommandExecutorService {
    private final Map<String, Command> commands;
    private final SessionManager sessionManager;

    public CommandExecutorService(Map<String, Command> commands, SessionManager sessionManager) {
        this.commands = commands;
        this.sessionManager = sessionManager;
    }

    public void executeCommand(String commandName, User user) {
        try {
            Session currentSession = sessionManager.createOrUpdateSession(user, commandName);
            Command command = commands.get(commandName);
            if (command != null && currentSession != null && !currentSession.isExpired()) {
                command.execute(user);
            } else if (command == null) {
                System.out.println("Invalid command. Please try again with a valid command.");
            }
        } catch (InsufficientRightsException e) {
            System.out.println(e.getMessage());
        } catch (SessionExpiredException e) {
            System.out.println("Session expired. Please login again.");
            sessionManager.invalidateSession(user);
        } catch (Exception e) {
            System.out.println("Unexpected error occurred: " + e.getMessage()); // Catch other unexpected exceptions.
        }
    }


}

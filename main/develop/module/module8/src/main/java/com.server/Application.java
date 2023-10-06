package com.server;

import com.server.command.Command;
import com.server.model.User;
import com.server.service.AuthenticationService;
import com.server.service.CommandExecutorService;
import com.server.service.SessionManager;
import com.server.util.CommandLoader;
import com.server.util.PasswordService;

import java.util.Map;

public class Application {
    public static void main(String[] args) {
        Map<String, User> users = TestDataLoader.loadUsers();
        Map<String, Command> commands = CommandLoader.loadCommands();

        SessionManager sessionManager = new SessionManager();
        AuthenticationService authService = new AuthenticationService(users, new PasswordService(), sessionManager);
        CommandExecutorService commandExecutorService = new CommandExecutorService(commands, sessionManager);

        CLI cli = new CLI(authService, commandExecutorService, sessionManager);
        cli.start();
    }
}

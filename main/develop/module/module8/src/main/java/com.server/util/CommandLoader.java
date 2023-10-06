package com.server.util;


import com.server.TestDataLoader;
import com.server.command.AdminCommand;
import com.server.command.Command;
import com.server.command.LogoutCommand;
import com.server.command.UserCommand;
import com.server.model.User;
import com.server.service.AuthenticationService;
import com.server.service.SessionManager;

import java.util.HashMap;
import java.util.Map;

public class CommandLoader {

    public static Map<String, Command> loadCommands() {
        Map<String, Command> commandMap = new HashMap<>();
        Command adminCommand = new AdminCommand();
        commandMap.put(adminCommand.getPath(), adminCommand);
//        ---Delimiter---
        Command userCommand = new UserCommand();
        commandMap.put(userCommand.getPath(), userCommand);
//        ---Delimiter---
        Map<String, User> users = TestDataLoader.loadUsers();
        SessionManager sessionManager = new SessionManager();
        AuthenticationService authService = new AuthenticationService(users, new PasswordService(), sessionManager);

        Command logoutCommand = new LogoutCommand(authService);
        commandMap.put(logoutCommand.getPath(), logoutCommand);

        return commandMap;
    }
}

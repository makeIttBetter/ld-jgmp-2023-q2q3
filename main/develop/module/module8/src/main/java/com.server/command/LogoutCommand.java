package com.server.command;

import com.server.model.User;
import com.server.service.AuthenticationService;

/**
 * Logout command for logging out the current user.
 * <p> This command is available for all users. </p>
 */
public class LogoutCommand extends BaseCommand {

    private final AuthenticationService authenticationService;

    public LogoutCommand(AuthenticationService authenticationService1) {
        super("/logout"); // Define the path associated with this command
        this.authenticationService = authenticationService1;
    }

    @Override
    public void execute(User user) {
        authenticationService.logOut(user);
    }
}

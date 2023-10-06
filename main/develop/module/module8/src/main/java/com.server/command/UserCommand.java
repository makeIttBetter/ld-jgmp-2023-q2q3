package com.server.command;

import com.server.model.User;

/**
 * Base class for simple users.
 * <p> This command is available only for simple users. </p>
 */
public class UserCommand extends BaseCommand {

    public UserCommand() {
        super("/user"); // Define the path associated with this command
    }

    @Override
    public void execute(User user) {
        System.out.println("Executing user command...");
    }
}

package com.server.command;

import com.server.model.User;

/**
 * Example command for administrators.
 * <p> This command is available only for administrators. </p>
 */
public class AdminCommand extends BaseCommand {

    public AdminCommand() {
        super("/admin"); // Define the path associated with this command
    }

    @Override
    public void execute(User user) {
        System.out.println("Executing admin command...");
    }
}
package com.server.command;

import com.server.model.User;

/**
 * Common interface for commands.
 * <p> Commands are executed by the server when a client sends a request. </p>
 */
public interface Command {
    /**
     * Executes the command.
     * <p> The implementation of this method should contain the logic of the command. </p>
     */
    void execute(User user);

    /**
     * Returns the path associated with the command.
     * <p> The path is used to identify the command. </p>
     */
    String getPath();
}
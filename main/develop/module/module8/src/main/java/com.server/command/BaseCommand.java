package com.server.command;

import lombok.Getter;

/**
 * Base class for commands.
 * <p> Contains the path associated with the command. </p>
 */
@Getter
public abstract class BaseCommand implements Command {
    private final String path;

    protected BaseCommand(String path) {
        this.path = path;
    }
}
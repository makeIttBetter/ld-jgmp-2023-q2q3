package com.server.exception;

/**
 * Exception thrown when a user does not have sufficient rights to execute a command.
 */
public class InsufficientRightsException extends RuntimeException {
    public InsufficientRightsException(String message) {
        super(message);
    }
}


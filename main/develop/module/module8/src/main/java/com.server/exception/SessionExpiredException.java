package com.server.exception;

/**
 * Exception thrown when a user session has expired.
 */
public class SessionExpiredException extends RuntimeException {
    public SessionExpiredException(String message) {
        super(message);
    }
}


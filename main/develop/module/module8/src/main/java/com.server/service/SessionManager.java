package com.server.service;


import com.server.config.ServerConfig;
import com.server.exception.InsufficientRightsException;
import com.server.exception.SessionExpiredException;
import com.server.model.Session;
import com.server.model.User;

import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Session manager service.
 * <p> Responsible for creating sessions for users. </p>
 */
public class SessionManager {

    // timeout in seconds
    private final long sessionTimeout;
    private final AccessChecker access = AccessChecker.getInstance();
    private static final ConcurrentHashMap<String, Session> activeSessions = new ConcurrentHashMap<>();

    public SessionManager() {
        ServerConfig serverConfig = ServerConfig.getInstance();
        this.sessionTimeout = serverConfig.getSessionTimeout();
    }

    /**
     * Creates a new session for the user if the user has access to the command.
     * If the user already has an active session, the lastUpdatedTime is updated.
     *
     * @param user         User for whom the session is being created
     * @param accessedPath Path of the command being accessed
     * @return Session object for the user
     * @throws InsufficientRightsException if the user does not have access to the command
     * @throws SessionExpiredException     if the user has an expired session
     */
    public Session createOrUpdateSession(User user, String accessedPath) {
        if (!access.mayAccess(user, accessedPath)) {
            throw new InsufficientRightsException("Insufficient rights for user: " + user.getUsername() + " on command: " + accessedPath);
        }

        // If session exists and is not expired, update lastUpdatedTime and return it
        return activeSessions.compute(user.getUsername(), (username, existingSession) -> {
            if (existingSession == null || !existingSession.isExpired()) {
                return new Session(user, getSessionTimeout());
            } else if (existingSession.isExpired()) {
                throw new SessionExpiredException("Session for user: " + user.getUsername() + " has expired. Please login again.");
            } else {
                existingSession.setLastUpdatedTime(LocalDateTime.now());
                return existingSession;
            }
        });
    }

    /**
     * Returns the active session for the user if it exists and is not expired.
     *
     * @param user User for whom the session is being checked
     * @return Session object for the user if it exists and is not expired, null otherwise
     */
    public Session getActiveSession(User user) {
        Session session = activeSessions.get(user.getUsername());
        return (session != null && !session.isExpired()) ? session : null;
    }

    /**
     * Invalidates the session for the user.
     *
     * @param user User for whom the session is being invalidated
     */
    public void invalidateSession(User user) {
        activeSessions.remove(user.getUsername());
    }

    public long getSessionTimeout() {
        return sessionTimeout;
    }

}




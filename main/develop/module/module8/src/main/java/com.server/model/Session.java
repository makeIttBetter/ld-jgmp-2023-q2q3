package com.server.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Session model.
 */
@Getter
@Setter
public class Session {
    private final User user;
    private final LocalDateTime creationTime;
    private LocalDateTime lastUpdatedTime;
    // timeout in seconds
    private final long sessionTimeoutMinutes;

    public Session(User user, long timeout) {
        this.user = user;
        this.creationTime = LocalDateTime.now();
        this.lastUpdatedTime = LocalDateTime.now();
        this.sessionTimeoutMinutes = timeout;
    }

    public boolean isExpired() {
        return lastUpdatedTime.plusMinutes(sessionTimeoutMinutes).isBefore(LocalDateTime.now());
    }
}


package com.server.service;

import com.server.config.ServerConfig;
import com.server.exception.InsufficientRightsException;
import com.server.exception.SessionExpiredException;
import com.server.model.Role;
import com.server.model.Session;
import com.server.model.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

public class SessionManagerTest {

    private SessionManager sessionManager;
    private AccessChecker accessChecker;
    private ServerConfig serverConfig;
    private User user;

    @Before
    public void setUp() {
        accessChecker = mock(AccessChecker.class);
        serverConfig = mock(ServerConfig.class);
        when(serverConfig.getSessionTimeout()).thenReturn(60L);

        // Mock AccessChecker's static method (if using Mockito 3.4.0 or higher)
        try (MockedStatic<AccessChecker> theMock = Mockito.mockStatic(AccessChecker.class)) {
            theMock.when(AccessChecker::getInstance).thenReturn(accessChecker);

            sessionManager = new SessionManager();  // The sessionManager will now use the mocked AccessChecker
        }

        user = new User("testUser", "testPass", Role.USER);
    }

    @Test
    public void testCreateOrUpdateSession_Success() {
        when(accessChecker.mayAccess(user, "/test")).thenReturn(true);

        Session session = sessionManager.createOrUpdateSession(user, "/test");
        assertNotNull(session);
        assertEquals(user, session.getUser());
    }

    @Test(expected = InsufficientRightsException.class)
    public void testCreateOrUpdateSession_InsufficientRights() {
        when(accessChecker.mayAccess(user, "/test")).thenReturn(false);

        sessionManager.createOrUpdateSession(user, "/test");
    }

    @Test
    public void testGetActiveSession_ValidSession() {
        when(accessChecker.mayAccess(user, "/test")).thenReturn(true);
        sessionManager.createOrUpdateSession(user, "/test");

        Session session = sessionManager.getActiveSession(user);
        assertNotNull(session);
        assertEquals(user, session.getUser());
    }

    @Test
    public void testGetActiveSession_ExpiredSession() throws NoSuchFieldException, IllegalAccessException {
        when(accessChecker.mayAccess(user, "/test")).thenReturn(true);

        // Create a session
        Session session = sessionManager.createOrUpdateSession(user, "/test");

        // Retrieve the created session using reflection and set its lastUpdatedTime to a past value
        Field lastUpdatedTimeField = Session.class.getDeclaredField("lastUpdatedTime");
        lastUpdatedTimeField.setAccessible(true);
        LocalDateTime pastTime = LocalDateTime.now().minusMinutes(61);  // Adjust the value to ensure the session is expired
        lastUpdatedTimeField.set(session, pastTime);

        // Now the session should be expired
        Session retrievedSession = sessionManager.getActiveSession(user);
        assertNull(retrievedSession);
    }

//    @Test(expected = SessionExpiredException.class)
//    public void testCreateOrUpdateSession_SessionExpired() throws NoSuchFieldException, IllegalAccessException {
//        when(accessChecker.mayAccess(user, "/test")).thenReturn(true);
//
//        // First, create a session.
//        Session initialSession = sessionManager.createOrUpdateSession(user, "/test");
//
//        // Retrieve the created session using reflection and set its lastUpdatedTime to a past value
//        Field lastUpdatedTimeField = Session.class.getDeclaredField("lastUpdatedTime");
//        lastUpdatedTimeField.setAccessible(true);
//        LocalDateTime pastTime = LocalDateTime.now().minusMinutes(61);  // Adjust the value to ensure the session is expired
//        lastUpdatedTimeField.set(initialSession, pastTime);
//
//        // This should throw a SessionExpiredException because the session has expired.
//        sessionManager.createOrUpdateSession(user, "/test");
//    }


    @Test
    public void testInvalidateSession() {
        when(accessChecker.mayAccess(user, "/test")).thenReturn(true);
        sessionManager.createOrUpdateSession(user, "/test");

        sessionManager.invalidateSession(user);
        assertNull(sessionManager.getActiveSession(user));
    }

}

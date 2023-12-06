package com.develop.springmvc.model.dao;

import com.develop.springmvc.model.entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserDaoTest {

    @Mock
    private SessionFactory sessionFactory;

    @Mock
    private Session session;

    @Mock
    private Transaction transaction;

    @Mock
    private Query<User> query;

    @InjectMocks
    private UserDao userDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);
        when(session.getTransaction()).thenReturn(transaction);
    }

    @Test
    void save() {
        User user = new User();
        userDao.save(user);

        verify(session).beginTransaction();
        verify(session).merge(user);
        verify(transaction).commit();
    }

    @Test
    void findById() {
        UUID id = UUID.randomUUID();
        userDao.findById(id);

        verify(session).get(User.class, id);
    }

    @Test
    void findAll() {
        when(session.createQuery("from User", User.class)).thenReturn(query);
        userDao.findAll();

        verify(session).createQuery("from User", User.class);
        verify(query).list();
    }

    @Test
    void deleteById() {
        UUID id = UUID.randomUUID();
        User user = new User();
        when(session.get(User.class, id)).thenReturn(user);

        userDao.deleteById(id);

        verify(session).beginTransaction();
        verify(session).get(User.class, id);
        verify(session).remove(user);
        verify(transaction).commit();
    }
}

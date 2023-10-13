package com.develop.springmvc.model.dao;

import com.develop.springmvc.model.entities.Event;
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

class EventDaoTest {

    @Mock
    private SessionFactory sessionFactory;

    @Mock
    private Session session;

    @Mock
    private Transaction transaction;

    @Mock
    private Query<Event> query;

    @InjectMocks
    private EventDao eventDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);
        when(session.getTransaction()).thenReturn(transaction);
    }

    @Test
    void save() {
        Event event = new Event();
        eventDao.save(event);

        verify(session).beginTransaction();
        verify(session).merge(event);
        verify(transaction).commit();
    }

    @Test
    void findById() {
        UUID id = UUID.randomUUID();
        eventDao.findById(id);

        verify(session).get(Event.class, id);
    }

    @Test
    void findAll() {
        when(session.createQuery("from Event", Event.class)).thenReturn(query);
        eventDao.findAll();

        verify(session).createQuery("from Event", Event.class);
        verify(query).list();
    }

    @Test
    void deleteById() {
        UUID id = UUID.randomUUID();
        Event event = new Event();
        when(session.get(Event.class, id)).thenReturn(event);

        eventDao.deleteById(id);

        verify(session).beginTransaction();
        verify(session).get(Event.class, id);
        verify(session).remove(event);
        verify(transaction).commit();
    }
}

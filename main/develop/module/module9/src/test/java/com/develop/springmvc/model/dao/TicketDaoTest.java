package com.develop.springmvc.model.dao;

import com.develop.springmvc.model.entities.Ticket;
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

class TicketDaoTest {

    @Mock
    private SessionFactory sessionFactory;

    @Mock
    private Session session;

    @Mock
    private Transaction transaction;

    @Mock
    private Query<Ticket> query;

    @InjectMocks
    private TicketDao ticketDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);
        when(session.getTransaction()).thenReturn(transaction);
    }

    @Test
    void save() {
        Ticket ticket = new Ticket();
        ticketDao.save(ticket);

        verify(session).beginTransaction();
        verify(session).merge(ticket);
        verify(transaction).commit();
    }

    @Test
    void findById() {
        UUID id = UUID.randomUUID();
        ticketDao.findById(id);

        verify(session).get(Ticket.class, id);
    }

    @Test
    void findAll() {
        when(session.createQuery("from Ticket", Ticket.class)).thenReturn(query);
        ticketDao.findAll();

        verify(session).createQuery("from Ticket", Ticket.class);
        verify(query).list();
    }

    @Test
    void deleteById() {
        UUID id = UUID.randomUUID();
        Ticket ticket = new Ticket();
        when(session.get(Ticket.class, id)).thenReturn(ticket);

        ticketDao.deleteById(id);

        verify(session).beginTransaction();
        verify(session).get(Ticket.class, id);
        verify(session).remove(ticket);
        verify(transaction).commit();
    }
}

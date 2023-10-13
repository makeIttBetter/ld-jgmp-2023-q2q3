package com.develop.springmvc.service;

import com.develop.springmvc.model.dao.TicketDao;
import com.develop.springmvc.model.entities.Ticket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TicketServiceTest {

    @Mock
    private TicketDao ticketDao;

    @InjectMocks
    private TicketService ticketService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save() {
        Ticket ticket = new Ticket();
        ticketService.save(ticket);

        verify(ticketDao, times(1)).save(ticket);
    }

    @Test
    void findById() {
        UUID id = UUID.randomUUID();
        when(ticketDao.findById(id)).thenReturn(Optional.of(new Ticket()));

        ticketService.findById(id);

        verify(ticketDao, times(1)).findById(id);
    }

    @Test
    void findAll() {
        List<Ticket> tickets = Arrays.asList(new Ticket(), new Ticket());
        when(ticketDao.findAll()).thenReturn(tickets);

        ticketService.findAll();

        verify(ticketDao, times(1)).findAll();
    }

    @Test
    void deleteById() {
        UUID id = UUID.randomUUID();
        ticketService.deleteById(id);

        verify(ticketDao, times(1)).deleteById(id);
    }
}

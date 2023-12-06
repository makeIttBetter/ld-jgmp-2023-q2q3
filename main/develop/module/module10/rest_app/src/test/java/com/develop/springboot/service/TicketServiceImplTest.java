package com.develop.springboot.service;

import com.develop.springboot.model.entities.Ticket;
import com.develop.springboot.model.repository.TicketRepository;
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

class TicketServiceImplTest {

    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private TicketServiceImpl ticketServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save() {
        Ticket ticket = new Ticket();
        ticketServiceImpl.save(ticket);

        verify(ticketRepository, times(1)).save(ticket);
    }

    @Test
    void findById() {
        UUID id = UUID.randomUUID();
        when(ticketRepository.findById(id)).thenReturn(Optional.of(new Ticket()));

        ticketServiceImpl.findById(id);

        verify(ticketRepository, times(1)).findById(id);
    }

    @Test
    void findAll() {
        List<Ticket> tickets = Arrays.asList(new Ticket(), new Ticket());
        when(ticketRepository.findAll()).thenReturn(tickets);

        ticketServiceImpl.findAll();

        verify(ticketRepository, times(1)).findAll();
    }

    @Test
    void deleteById() {
        UUID id = UUID.randomUUID();
        ticketServiceImpl.deleteById(id);

        verify(ticketRepository, times(1)).deleteById(id);
    }
}

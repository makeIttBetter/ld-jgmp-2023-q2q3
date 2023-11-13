package com.develop.springboot.service;

import com.develop.springboot.model.entities.Ticket;
import com.develop.springboot.model.repository.TicketRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@org.springframework.stereotype.Service
public class TicketServiceImpl implements Service<Ticket, UUID> {

    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public void save(Ticket ticket) {
        log.info("Attempting to save ticket: {}", ticket);
        ticketRepository.save(ticket);
        log.info("Ticket saved successfully: {}", ticket);
    }

    @Override
    public Optional<Ticket> findById(UUID id) {
        log.info("Searching for ticket by id: {}", id);
        Optional<Ticket> ticket = ticketRepository.findById(id);
        log.info("Ticket search result for id {}: {}", id, ticket);
        return ticket;
    }

    @Override
    public List<Ticket> findAll() {
        log.info("Retrieving all tickets");
        List<Ticket> tickets = ticketRepository.findAll();
        log.info("Found {} tickets", tickets.size());
        return tickets;
    }

    @Override
    public void deleteById(UUID id) {
        log.info("Deleting ticket by id: {}", id);
        ticketRepository.deleteById(id);
        log.info("Ticket with id {} deleted successfully", id);
    }
}

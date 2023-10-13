package com.develop.springmvc.service;

import com.develop.springmvc.model.dao.TicketDao;
import com.develop.springmvc.model.entities.Ticket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@org.springframework.stereotype.Service
public class TicketService implements Service<Ticket, UUID> {

    @Autowired
    private TicketDao ticketDao;

    @Override
    public void save(Ticket ticket) {
        log.info("Attempting to save ticket: {}", ticket);
        ticketDao.save(ticket);
        log.info("Ticket saved successfully: {}", ticket);
    }

    @Override
    public Optional<Ticket> findById(UUID id) {
        log.info("Searching for ticket by id: {}", id);
        Optional<Ticket> ticket = ticketDao.findById(id);
        log.info("Ticket search result for id {}: {}", id, ticket);
        return ticket;
    }

    @Override
    public List<Ticket> findAll() {
        log.info("Retrieving all tickets");
        List<Ticket> tickets = ticketDao.findAll();
        log.info("Found {} tickets", tickets.size());
        return tickets;
    }

    @Override
    public void deleteById(UUID id) {
        log.info("Deleting ticket by id: {}", id);
        ticketDao.deleteById(id);
        log.info("Ticket with id {} deleted successfully", id);
    }
}

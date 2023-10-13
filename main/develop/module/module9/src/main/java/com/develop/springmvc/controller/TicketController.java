package com.develop.springmvc.controller;

import com.develop.springmvc.dto.TicketDTO;
import com.develop.springmvc.facade.BookingFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/tickets")
public class TicketController implements CrudController<TicketDTO, UUID> {

    @Autowired
    private BookingFacade bookingFacade;

    @Override
    public ResponseEntity<TicketDTO> create(TicketDTO ticketDTO) {
        log.info("Creating new Ticket: {}", ticketDTO);
        bookingFacade.saveTicket(ticketDTO);
        return new ResponseEntity<>(ticketDTO, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<TicketDTO> getById(UUID id) {
        log.info("Fetching Ticket by ID: {}", id);
        Optional<TicketDTO> ticketDTO = bookingFacade.findTicketById(id);
        return ticketDTO.map(value -> {
            log.info("Ticket found: {}", value);
            return new ResponseEntity<>(value, HttpStatus.OK);
        }).orElseGet(() -> {
            log.warn("Ticket not found for ID: {}", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        });
    }

    @Override
    public ResponseEntity<List<TicketDTO>> getAll() {
        log.info("Fetching all Tickets");
        return new ResponseEntity<>(bookingFacade.findAllTickets(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TicketDTO> update(UUID id, TicketDTO updatedTicketDTO) {
        log.info("Updating Ticket with ID: {}, Data: {}", id, updatedTicketDTO);
        updatedTicketDTO.setId(id);
        bookingFacade.saveTicket(updatedTicketDTO);
        return new ResponseEntity<>(updatedTicketDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> delete(UUID id) {
        log.info("Deleting Ticket with ID: {}", id);
        bookingFacade.deleteTicketById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

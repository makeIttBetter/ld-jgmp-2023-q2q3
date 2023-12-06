package com.develop.springboot.controller.api;

import com.develop.springboot.controller.CrudController;
import com.develop.springboot.dto.TicketDTO;
import com.develop.springboot.facade.BookingFacade;
import io.micrometer.core.annotation.Counted;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * ApiTicketController class is a REST controller for Ticket entity.
 * It provides CRUD operations for Ticket entity.
 */
@Slf4j
@RestController
@RequestMapping("/api/tickets")
public class ApiTicketController implements CrudController<TicketDTO, UUID> {

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

    @Counted(value = "ticket.getAll.count", description = "Counting how many times the getAll method has been invoked")
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

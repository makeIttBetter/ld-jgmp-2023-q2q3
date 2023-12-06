package com.develop.springboot.facade;

import com.develop.springboot.converter.EventConverter;
import com.develop.springboot.converter.TicketConverter;
import com.develop.springboot.converter.UserConverter;
import com.develop.springboot.dto.EventDTO;
import com.develop.springboot.dto.TicketDTO;
import com.develop.springboot.dto.UserDTO;
import com.develop.springboot.model.entities.Event;
import com.develop.springboot.model.entities.Ticket;
import com.develop.springboot.model.entities.User;
import com.develop.springboot.service.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * BookingFacadeImpl class is an implementation of BookingFacade interface.
 * It provides methods for saving, finding and deleting users, events and tickets.
 * Uses DTOs for data transfer.
 * Uses UUID for identifying entities.
 * Uses Optional for returning entities.
 */
@Slf4j
@Component
public class BookingFacadeImpl implements BookingFacade {

    @Autowired
    private Service<User, UUID> userService;

    @Autowired
    private Service<Event, UUID> eventService;

    @Autowired
    private Service<Ticket, UUID> ticketService;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private EventConverter eventConverter;

    @Autowired
    private TicketConverter ticketConverter;

    // User methods
    @Override
    public void saveUser(UserDTO userDTO) {
        log.info("Saving user: {}", userDTO);
        User user = userConverter.convertToUserEntity(userDTO);
        userService.save(user);
        userDTO.setId(user.getId());
        log.info("Saved user: {}", userDTO);
    }

    @Override
    public Optional<UserDTO> findUserById(UUID id) {
        log.info("Finding user by id: {}", id);
        return userService.findById(id).map(userConverter::convertToUserDTO);
    }

    @Override
    public List<UserDTO> findAllUsers() {
        log.info("Finding all users");
        return userService.findAll().stream()
                .map(userConverter::convertToUserDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUserById(UUID id) {
        log.info("Deleting user by id: {}", id);
        userService.deleteById(id);
    }

    // Event methods
    @Override
    public void saveEvent(EventDTO eventDTO) {
        log.info("Saving event: {}", eventDTO);
        Event event = eventConverter.convertToEventEntity(eventDTO);
        eventService.save(event);
        eventDTO.setId(event.getId());
        log.info("Saved event: {}", eventDTO);
    }

    @Override
    public Optional<EventDTO> findEventById(UUID id) {
        log.info("Finding event by id: {}", id);
        return eventService.findById(id).map(eventConverter::convertToEventDTO);
    }

    @Override
    public List<EventDTO> findAllEvents() {
        log.info("Finding all events");
        return eventService.findAll().stream()
                .map(eventConverter::convertToEventDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteEventById(UUID id) {
        log.info("Deleting event by id: {}", id);
        eventService.deleteById(id);
    }

    // Ticket methods
    @Override
    public void saveTicket(TicketDTO ticketDTO) {
        log.info("Saving ticket: {}", ticketDTO);
        Ticket ticket = ticketConverter.convertToTicketEntity(ticketDTO);
        ticketService.save(ticket);
        ticketDTO.setId(ticket.getId());
        log.info("Saved ticket: {}", ticketDTO);
    }

    @Override
    public Optional<TicketDTO> findTicketById(UUID id) {
        log.info("Finding ticket by id: {}", id);
        return ticketService.findById(id).map(ticketConverter::convertToTicketDTO);
    }

    @Override
    public List<TicketDTO> findAllTickets() {
        log.info("Finding all tickets");
        return ticketService.findAll().stream()
                .map(ticketConverter::convertToTicketDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteTicketById(UUID id) {
        log.info("Deleting ticket by id: {}", id);
        ticketService.deleteById(id);
    }
}

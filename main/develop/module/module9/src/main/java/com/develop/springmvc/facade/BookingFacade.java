package com.develop.springmvc.facade;

import com.develop.springmvc.dto.EventDTO;
import com.develop.springmvc.dto.TicketDTO;
import com.develop.springmvc.dto.UserDTO;
import com.develop.springmvc.model.entities.Event;
import com.develop.springmvc.model.entities.Ticket;
import com.develop.springmvc.model.entities.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookingFacade {
    void saveUser(UserDTO user);
    Optional<UserDTO> findUserById(UUID id);
    List<UserDTO> findAllUsers();
    void deleteUserById(UUID id);

    void saveEvent(EventDTO event);
    Optional<EventDTO> findEventById(UUID id);
    List<EventDTO> findAllEvents();
    void deleteEventById(UUID id);

    void saveTicket(TicketDTO ticket);
    Optional<TicketDTO> findTicketById(UUID id);
    List<TicketDTO> findAllTickets();
    void deleteTicketById(UUID id);
}

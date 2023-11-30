package com.develop.springboot.converter;

import com.develop.springboot.dto.TicketDTO;
import com.develop.springboot.model.entities.Event;
import com.develop.springboot.model.entities.Ticket;
import com.develop.springboot.model.entities.User;
import com.develop.springboot.service.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * TicketConverter class is a converter for Ticket entity.
 * It provides methods for converting Ticket entity to TicketDTO and vice versa.
 */
@Slf4j
@Component
public class TicketConverter {

    @Autowired
    private Service<User, UUID> userService;

    @Autowired
    private Service<Event, UUID> eventService;

    public TicketDTO convertToTicketDTO(Ticket ticket) {
        log.debug("Converting Ticket entity to TicketDTO: {}", ticket);
        TicketDTO dto = new TicketDTO();
        dto.setId(ticket.getId());
        dto.setUserId(ticket.getUser().getId());
        dto.setEventId(ticket.getEvent().getId());
        dto.setPlace(ticket.getPlace());
        return dto;
    }

    public Ticket convertToTicketEntity(TicketDTO dto) {
        log.debug("Converting TicketDTO to Ticket entity: {}", dto);
        Ticket ticket = new Ticket();
        ticket.setId(dto.getId());
        userService.findById(dto.getUserId()).ifPresent(ticket::setUser);
        eventService.findById(dto.getEventId()).ifPresent(ticket::setEvent);
        ticket.setPlace(dto.getPlace());
        return ticket;
    }

}

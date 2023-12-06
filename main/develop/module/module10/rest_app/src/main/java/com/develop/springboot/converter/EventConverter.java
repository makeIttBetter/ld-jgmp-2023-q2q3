package com.develop.springboot.converter;

import com.develop.springboot.dto.EventDTO;
import com.develop.springboot.model.entities.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * EventConverter class is a converter for Event entity.
 * It provides methods for converting Event entity to EventDTO and vice versa.
 */
@Slf4j
@Component
public class EventConverter {

    public EventDTO convertToEventDTO(Event event) {
        log.debug("Converting Event entity to EventDTO: {}", event);
        EventDTO dto = new EventDTO();
        dto.setId(event.getId());
        dto.setTitle(event.getTitle());
        dto.setDate(event.getDate());
        dto.setPrice(event.getPrice());
        return dto;
    }

    public Event convertToEventEntity(EventDTO dto) {
        log.debug("Converting EventDTO to Event entity: {}", dto);
        Event event = new Event();
        event.setId(dto.getId());
        event.setTitle(dto.getTitle());
        event.setDate(dto.getDate());
        event.setPrice(dto.getPrice());
        return event;
    }
}

package com.develop.springboot.converter;

import com.develop.springboot.dto.EventDTO;
import com.develop.springboot.model.entities.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EventConverter {

    public EventDTO convertToEventDTO(Event event) {
        log.debug("Converting Event entity to EventDTO: {}", event);
        EventDTO dto = new EventDTO();
        dto.setId(event.getId());
        dto.setTitle(event.getTitle());
        dto.setDate(event.getDate());
        return dto;
    }

    public Event convertToEventEntity(EventDTO dto) {
        log.debug("Converting EventDTO to Event entity: {}", dto);
        Event event = new Event();
        event.setId(dto.getId());
        event.setTitle(dto.getTitle());
        event.setDate(dto.getDate());
        return event;
    }
}

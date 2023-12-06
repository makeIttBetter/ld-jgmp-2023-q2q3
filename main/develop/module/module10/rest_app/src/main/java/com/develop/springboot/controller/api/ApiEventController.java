package com.develop.springboot.controller.api;

import com.develop.springboot.controller.CrudController;
import com.develop.springboot.dto.EventDTO;
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
 * ApiEventController class is a REST controller for Event entity.
 * It provides CRUD operations for Event entity.
 */
@Slf4j
@RestController
@RequestMapping("/api/events")
public class ApiEventController implements CrudController<EventDTO, UUID> {

    @Autowired
    private BookingFacade bookingFacade;

    @Override
    public ResponseEntity<EventDTO> create(EventDTO eventDTO) {
        log.info("Creating new Event: {}", eventDTO);
        bookingFacade.saveEvent(eventDTO);
        return new ResponseEntity<>(eventDTO, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<EventDTO> getById(UUID id) {
        log.info("Fetching Event by ID: {}", id);
        Optional<EventDTO> eventDTO = bookingFacade.findEventById(id);
        return eventDTO.map(value -> {
            log.info("Event found: {}", value);
            return new ResponseEntity<>(value, HttpStatus.OK);
        }).orElseGet(() -> {
            log.warn("Event not found for ID: {}", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        });
    }

    @Counted(value = "event.getAll.count", description = "Counting how many times the getAll method has been invoked")
    @Override
    public ResponseEntity<List<EventDTO>> getAll() {
        log.info("Fetching all Events");
        return new ResponseEntity<>(bookingFacade.findAllEvents(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<EventDTO> update(UUID id, EventDTO updatedEventDTO) {
        log.info("Updating Event with ID: {}, Data: {}", id, updatedEventDTO);
        updatedEventDTO.setId(id);
        bookingFacade.saveEvent(updatedEventDTO);
        return new ResponseEntity<>(updatedEventDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> delete(UUID id) {
        log.info("Deleting Event with ID: {}", id);
        bookingFacade.deleteEventById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

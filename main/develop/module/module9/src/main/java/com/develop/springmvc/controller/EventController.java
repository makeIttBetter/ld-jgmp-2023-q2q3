package com.develop.springmvc.controller;

import com.develop.springmvc.dto.EventDTO;
import com.develop.springmvc.facade.BookingFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Controller
@RequestMapping("/api/events")
public class EventController implements CrudController<EventDTO, UUID> {

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

    @GetMapping("/test")
    public String showTestPage(Model model) {
        model.addAttribute("message", "This is a test page!");
        return "test";
    }

    @GetMapping("/testError")
    public String throwError() {
        throw new RuntimeException("This is a test error!");
    }


}

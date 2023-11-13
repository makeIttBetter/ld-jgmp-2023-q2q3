package com.develop.springboot.service;

import com.develop.springboot.model.entities.Event;
import com.develop.springboot.model.repository.EventRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@org.springframework.stereotype.Service
public class EventServiceImpl implements Service<Event, UUID> {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public void save(Event event) {
        log.info("Attempting to save event: {}", event);
        eventRepository.save(event);
        log.info("Event saved successfully: {}", event);
    }

    @Override
    public Optional<Event> findById(UUID id) {
        log.info("Searching for event by id: {}", id);
        Optional<Event> event = eventRepository.findById(id);
        log.info("Event search result for id {}: {}", id, event);
        return event;
    }

    @Override
    public List<Event> findAll() {
        log.info("Retrieving all events");
        List<Event> events = eventRepository.findAll();
        log.info("Found {} events", events.size());
        return events;
    }

    @Override
    public void deleteById(UUID id) {
        log.info("Deleting event by id: {}", id);
        eventRepository.deleteById(id);
        log.info("Event with id {} deleted successfully", id);
    }
}

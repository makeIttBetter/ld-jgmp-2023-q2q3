package com.develop.springmvc.service;

import com.develop.springmvc.model.dao.EventDao;
import com.develop.springmvc.model.entities.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@org.springframework.stereotype.Service
public class EventService implements Service<Event, UUID> {

    @Autowired
    private EventDao eventDao;

    @Override
    public void save(Event event) {
        log.info("Attempting to save event: {}", event);
        eventDao.save(event);
        log.info("Event saved successfully: {}", event);
    }

    @Override
    public Optional<Event> findById(UUID id) {
        log.info("Searching for event by id: {}", id);
        Optional<Event> event = eventDao.findById(id);
        log.info("Event search result for id {}: {}", id, event);
        return event;
    }

    @Override
    public List<Event> findAll() {
        log.info("Retrieving all events");
        List<Event> events = eventDao.findAll();
        log.info("Found {} events", events.size());
        return events;
    }

    @Override
    public void deleteById(UUID id) {
        log.info("Deleting event by id: {}", id);
        eventDao.deleteById(id);
        log.info("Event with id {} deleted successfully", id);
    }
}

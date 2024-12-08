package com.develop.springboot.service;

import com.develop.springboot.model.entities.Event;
import com.develop.springboot.model.repository.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class EventServiceImplTest {

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventServiceImpl eventServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save() {
        Event event = new Event();
        eventServiceImpl.save(event);

        verify(eventRepository, times(1)).save(event);
    }

    @Test
    void findById() {
        UUID id = UUID.randomUUID();
        eventServiceImpl.findById(id);

        verify(eventRepository, times(1)).findById(id);
    }

    @Test
    void findAll() {
        List<Event> events = Arrays.asList(new Event(), new Event());
        when(eventRepository.findAll()).thenReturn(events);

        eventServiceImpl.findAll();

        verify(eventRepository, times(1)).findAll();
    }

    @Test
    void deleteById() {
        UUID id = UUID.randomUUID();
        eventServiceImpl.deleteById(id);

        verify(eventRepository, times(1)).deleteById(id);
    }
}

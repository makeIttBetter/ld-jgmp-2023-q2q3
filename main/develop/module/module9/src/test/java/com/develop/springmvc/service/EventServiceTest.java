package com.develop.springmvc.service;

import com.develop.springmvc.model.dao.EventDao;
import com.develop.springmvc.model.entities.Event;
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

class EventServiceTest {

    @Mock
    private EventDao eventDao;

    @InjectMocks
    private EventService eventService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save() {
        Event event = new Event();
        eventService.save(event);

        verify(eventDao, times(1)).save(event);
    }

    @Test
    void findById() {
        UUID id = UUID.randomUUID();
        eventService.findById(id);

        verify(eventDao, times(1)).findById(id);
    }

    @Test
    void findAll() {
        List<Event> events = Arrays.asList(new Event(), new Event());
        when(eventDao.findAll()).thenReturn(events);

        eventService.findAll();

        verify(eventDao, times(1)).findAll();
    }

    @Test
    void deleteById() {
        UUID id = UUID.randomUUID();
        eventService.deleteById(id);

        verify(eventDao, times(1)).deleteById(id);
    }
}

package com.develop.springmvc.model.dao;

import com.develop.springmvc.model.entities.Event;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Repository
public class EventDao implements DAO<Event, UUID> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Event event) {
        try (Session session = sessionFactory.openSession()) {
            log.info("Attempting to save event: {}", event);
            session.beginTransaction();
            Event savedEvent = session.merge(event);
            session.getTransaction().commit();
            event.setId(savedEvent.getId());
            log.info("Successfully saved event: {}", event);
        } catch (Exception e) {
            log.error("Error saving event: {}", event, e);
        }
    }

    @Override
    public Optional<Event> findById(UUID id) {
        try (Session session = sessionFactory.openSession()) {
            log.info("Attempting to find event by id: {}", id);
            Optional<Event> result = Optional.ofNullable(session.get(Event.class, id));
            log.info("Result for id {}: {}", id, result);
            return result;
        } catch (Exception e) {
            log.error("Error finding event by id: {}", id, e);
            return Optional.empty();
        }
    }

    @Override
    public List<Event> findAll() {
        try (Session session = sessionFactory.openSession()) {
            log.info("Attempting to retrieve all events.");
            List<Event> events = session.createQuery("from Event", Event.class).list();
            log.info("Found {} events.", events.size());
            return events;
        } catch (Exception e) {
            log.error("Error retrieving all events", e);
            return List.of();
        }
    }

    @Override
    public void deleteById(UUID id) {
        try (Session session = sessionFactory.openSession()) {
            log.info("Attempting to delete event by id: {}", id);
            session.beginTransaction();
            Event event = session.get(Event.class, id);
            if (event != null) {
                session.remove(event);
                log.info("Successfully deleted event with id: {}", id);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            log.error("Error deleting event by id: {}", id, e);
        }
    }
}

package com.develop.springmvc.model.dao;

import com.develop.springmvc.model.entities.Ticket;
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
public class TicketDao implements DAO<Ticket, UUID> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Ticket ticket) {
        try (Session session = sessionFactory.openSession()) {
            log.info("Attempting to save ticket: {}", ticket);
            session.beginTransaction();
            Ticket savedTicket = session.merge(ticket);
            session.getTransaction().commit();
            ticket.setId(savedTicket.getId());
            log.info("Successfully saved ticket: {}", ticket);
        } catch (Exception e) {
            log.error("Error saving ticket: {}", ticket, e);
        }
    }

    @Override
    public Optional<Ticket> findById(UUID id) {
        try (Session session = sessionFactory.openSession()) {
            log.info("Attempting to find ticket by id: {}", id);
            Optional<Ticket> result = Optional.ofNullable(session.get(Ticket.class, id));
            log.info("Result for id {}: {}", id, result);
            return result;
        } catch (Exception e) {
            log.error("Error finding ticket by id: {}", id, e);
            return Optional.empty();
        }
    }

    @Override
    public List<Ticket> findAll() {
        try (Session session = sessionFactory.openSession()) {
            log.info("Attempting to retrieve all tickets.");
            List<Ticket> tickets = session.createQuery("from Ticket", Ticket.class).list();
            log.info("Found {} tickets.", tickets.size());
            return tickets;
        } catch (Exception e) {
            log.error("Error retrieving all tickets", e);
            return List.of();
        }
    }

    @Override
    public void deleteById(UUID id) {
        try (Session session = sessionFactory.openSession()) {
            log.info("Attempting to delete ticket by id: {}", id);
            session.beginTransaction();
            Ticket ticket = session.get(Ticket.class, id);
            if (ticket != null) {
                session.remove(ticket);
                log.info("Successfully deleted ticket with id: {}", id);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            log.error("Error deleting ticket by id: {}", id, e);
        }
    }
}

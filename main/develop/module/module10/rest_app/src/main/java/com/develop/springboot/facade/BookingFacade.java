package com.develop.springboot.facade;

import com.develop.springboot.dto.EventDTO;
import com.develop.springboot.dto.TicketDTO;
import com.develop.springboot.dto.UserDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Facade for booking.
 * <p> Contains methods for saving, finding and deleting users, events and tickets.
 * <p> Uses DTOs for data transfer.
 * <p> Uses UUID for identifying entities.
 * <p> Uses Optional for returning entities.
 */
public interface BookingFacade {
    /**
     * Saves user.
     *
     * @param user user to save
     */
    void saveUser(UserDTO user);

    /**
     * Finds user by id.
     *
     * @param id id of user to find
     * @return optional of user
     */
    Optional<UserDTO> findUserById(UUID id);

    /**
     * Finds all users.
     *
     * @return list of users
     */
    List<UserDTO> findAllUsers();

    /**
     * Deletes user by id.
     *
     * @param id id of user to delete
     */
    void deleteUserById(UUID id);

    /**
     * Saves event.
     *
     * @param event event to save
     */
    void saveEvent(EventDTO event);

    /**
     * Finds event by id.
     *
     * @param id id of event to find
     * @return optional of event
     */
    Optional<EventDTO> findEventById(UUID id);

    /**
     * Finds all events.
     *
     * @return list of events
     */
    List<EventDTO> findAllEvents();

    /**
     * Deletes event by id.
     *
     * @param id id of event to delete
     */
    void deleteEventById(UUID id);

    /**
     * Saves ticket.
     *
     * @param ticket ticket to save
     */
    void saveTicket(TicketDTO ticket);

    /**
     * Finds ticket by id.
     *
     * @param id id of ticket to find
     * @return optional of ticket
     */
    Optional<TicketDTO> findTicketById(UUID id);

    /**
     * Finds all tickets.
     *
     * @return list of tickets
     */
    List<TicketDTO> findAllTickets();

    /**
     * Deletes ticket by id.
     *
     * @param id id of ticket to delete
     */
    void deleteTicketById(UUID id);
}

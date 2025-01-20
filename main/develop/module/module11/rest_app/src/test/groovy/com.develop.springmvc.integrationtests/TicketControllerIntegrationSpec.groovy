package com.develop.springboot.integrationtests

import com.develop.springboot.dto.EventDTO
import com.develop.springboot.dto.TicketDTO
import com.develop.springboot.dto.UserDTO
import com.develop.springboot.facade.BookingFacade
import org.springframework.context.ApplicationContext
import org.springframework.context.support.ClassPathXmlApplicationContext
import org.springframework.test.annotation.DirtiesContext
import spock.lang.Specification

import java.time.LocalDateTime

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class TicketControllerIntegrationSpec extends Specification {

    private ApplicationContext applicationContext
    private BookingFacade bookingFacade

    def setup() {
        applicationContext = new ClassPathXmlApplicationContext("test-spring-config.xml")
        bookingFacade = applicationContext.getBean(BookingFacade)
    }

    def "test create and fetch ticket"() {
        given: "a new user and event"
        UserDTO user = new UserDTO(name: 'John Doe', email: 'john@example.com')
        EventDTO event = new EventDTO(title: 'Music Fest', date: LocalDateTime.now())
        bookingFacade.saveUser(user)
        bookingFacade.saveEvent(event)

        and: "a new ticket for the user and event"
        TicketDTO ticket = new TicketDTO(userId: user.id, eventId: event.id, place: 5)

        when: "the ticket is saved"
        bookingFacade.saveTicket(ticket)

        then: "it should be retrievable"
        Optional<TicketDTO> retrievedTicket = bookingFacade.findTicketById(ticket.id)
        retrievedTicket.isPresent() && retrievedTicket.get().place == 5
    }

    def "test cancel a ticket"() {
        given: "an existing ticket"
        UserDTO user = new UserDTO(name: 'Jane Doe', email: 'jane@example.com')
        EventDTO event = new EventDTO(title: 'Music Concert', date: LocalDateTime.now())
        bookingFacade.saveUser(user)
        bookingFacade.saveEvent(event)
        TicketDTO ticket = new TicketDTO(userId: user.id, eventId: event.id, place: 6)
        bookingFacade.saveTicket(ticket)

        when: "the ticket is canceled"
        bookingFacade.deleteTicketById(ticket.id)

        then: "it should not be retrievable"
        !bookingFacade.findTicketById(ticket.id).isPresent()
    }
}

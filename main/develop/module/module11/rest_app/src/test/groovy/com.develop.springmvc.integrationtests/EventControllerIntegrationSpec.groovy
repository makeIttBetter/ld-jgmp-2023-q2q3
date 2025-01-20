package com.develop.springboot.integrationtests

import com.develop.springboot.dto.EventDTO
import com.develop.springboot.facade.BookingFacade
import org.springframework.context.ApplicationContext
import org.springframework.context.support.ClassPathXmlApplicationContext
import org.springframework.test.annotation.DirtiesContext
import spock.lang.Specification

import java.time.LocalDateTime

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class EventControllerIntegrationSpec extends Specification {

    private ApplicationContext applicationContext
    private BookingFacade bookingFacade

    def setup() {
        applicationContext = new ClassPathXmlApplicationContext("test-spring-config.xml")
        bookingFacade = applicationContext.getBean(BookingFacade)
    }

    def "test create and fetch event"() {
        given: "a new event"
        EventDTO event = new EventDTO(title: 'Music Fest', date: LocalDateTime.now())

        when: "the event is saved"
        bookingFacade.saveEvent(event)

        then: "it should be retrievable"
        Optional<EventDTO> retrievedEvent = bookingFacade.findEventById(event.id)
        retrievedEvent.isPresent() && retrievedEvent.get().title == 'Music Fest'
    }
}

package com.develop.springboot.integrationtests

import com.develop.springboot.dto.UserDTO
import com.develop.springboot.facade.BookingFacade
import org.springframework.context.ApplicationContext
import org.springframework.context.support.ClassPathXmlApplicationContext
import org.springframework.test.annotation.DirtiesContext
import spock.lang.Specification

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class UserControllerIntegrationSpec extends Specification {

    private ApplicationContext applicationContext
    private BookingFacade bookingFacade

    def setup() {
        applicationContext = new ClassPathXmlApplicationContext("test-spring-config.xml")
        bookingFacade = applicationContext.getBean(BookingFacade)
    }

    def "test create and fetch user"() {
        given: "a new user"
        UserDTO user = new UserDTO(name: 'John Doe', email: 'john@example.com')

        when: "the user is saved"
        bookingFacade.saveUser(user)

        then: "it should be retrievable"
        Optional<UserDTO> retrievedUser = bookingFacade.findUserById(user.id)
        retrievedUser.isPresent() && retrievedUser.get().name == 'John Doe'
    }
}

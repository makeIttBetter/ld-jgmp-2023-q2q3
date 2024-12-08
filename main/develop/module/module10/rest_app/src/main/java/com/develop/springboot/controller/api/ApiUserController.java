package com.develop.springboot.controller.api;

import com.develop.springboot.controller.CrudController;
import com.develop.springboot.dto.UserDTO;
import com.develop.springboot.facade.BookingFacade;
import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * ApiUserController class is a REST controller for User entity.
 * It provides CRUD operations for User entity.
 */
@Slf4j
@RestController
@RequestMapping("/api/users")
public class ApiUserController implements CrudController<UserDTO, UUID> {

    @Autowired
    private BookingFacade bookingFacade;

    @Override
    public ResponseEntity<UserDTO> create(UserDTO userDTO) {
        log.info("Creating new User: {}", userDTO);
        bookingFacade.saveUser(userDTO);
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<UserDTO> getById(UUID id) {
        log.info("Fetching User by ID: {}", id);
        Optional<UserDTO> userDTO = bookingFacade.findUserById(id);
        return userDTO.map(value -> {
            log.info("User found: {}", value);
            return new ResponseEntity<>(value, HttpStatus.OK);
        }).orElseGet(() -> {
            log.warn("User not found for ID: {}", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        });
    }

    @Counted(value = "user.getAll.count", description = "Counting how many times the getAll method has been invoked")
    @Override
    public ResponseEntity<List<UserDTO>> getAll() {
        log.info("Fetching all Users");
        return new ResponseEntity<>(bookingFacade.findAllUsers(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserDTO> update(UUID id, UserDTO updatedUserDTO) {
        log.info("Updating User with ID: {}, Data: {}", id, updatedUserDTO);
        updatedUserDTO.setId(id);
        bookingFacade.saveUser(updatedUserDTO);
        return new ResponseEntity<>(updatedUserDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> delete(UUID id) {
        log.info("Deleting User with ID: {}", id);
        bookingFacade.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

package com.develop.springboot.service;

import com.develop.springboot.model.entities.User;
import com.develop.springboot.model.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save() {
        User user = new User();
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        userServiceImpl.save(user);

        verify(userRepository, times(1)).save(user);
    }

    @Test
    void findById() {
        UUID id = UUID.randomUUID();
        when(userRepository.findById(id)).thenReturn(Optional.of(new User()));

        userServiceImpl.findById(id);

        verify(userRepository, times(1)).findById(id);
    }

    @Test
    void findAll() {
        List<User> users = Arrays.asList(new User(), new User());
        when(userRepository.findAll()).thenReturn(users);

        userServiceImpl.findAll();

        verify(userRepository, times(1)).findAll();
    }

    @Test
    void deleteById() {
        UUID id = UUID.randomUUID();
        userServiceImpl.deleteById(id);

        verify(userRepository, times(1)).deleteById(id);
    }
}

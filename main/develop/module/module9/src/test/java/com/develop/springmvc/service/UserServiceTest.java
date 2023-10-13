package com.develop.springmvc.service;

import com.develop.springmvc.model.dao.UserDao;
import com.develop.springmvc.model.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserServiceTest {

    @Mock
    private UserDao userDao;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save() {
        User user = new User();
        userService.save(user);

        verify(userDao, times(1)).save(user);
    }

    @Test
    void findById() {
        UUID id = UUID.randomUUID();
        when(userDao.findById(id)).thenReturn(Optional.of(new User()));

        userService.findById(id);

        verify(userDao, times(1)).findById(id);
    }

    @Test
    void findAll() {
        List<User> users = Arrays.asList(new User(), new User());
        when(userDao.findAll()).thenReturn(users);

        userService.findAll();

        verify(userDao, times(1)).findAll();
    }

    @Test
    void deleteById() {
        UUID id = UUID.randomUUID();
        userService.deleteById(id);

        verify(userDao, times(1)).deleteById(id);
    }
}

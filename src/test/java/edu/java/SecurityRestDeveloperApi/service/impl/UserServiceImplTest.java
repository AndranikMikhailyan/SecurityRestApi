package edu.java.SecurityRestDeveloperApi.service.impl;

import edu.java.SecurityRestDeveloperApi.model.Status;
import edu.java.SecurityRestDeveloperApi.model.User;
import edu.java.SecurityRestDeveloperApi.repository.UserRepository;
import edu.java.SecurityRestDeveloperApi.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    UserRepository userRepository = mock(UserRepository.class);
    BCryptPasswordEncoder passwordEncoder = mock(BCryptPasswordEncoder.class);
    UserService userService = new UserServiceImpl(userRepository, passwordEncoder);
    User user = new User();

    @BeforeEach
    public void setUp() {
        user.setUsername("login");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        when(userRepository.save(user)).thenReturn(user);
        when(passwordEncoder.encode(user.getPassword())).thenReturn(new StringBuilder(user.getPassword()).reverse().toString());
    }

    @Test
    void register() {
        User actual = userService.register(user);
        assertEquals(Status.NOT_ACTIVE, actual.getStatus());
        assertEquals("drowssap", actual.getPassword());
    }

    @Test
    void getAll() {
        userService.getAll();
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void findByUsername() {
        userService.findByUsername("login");
        verify(userRepository, times(1)).findByUsername("login");
    }

    @Test
    void findById() {
        userService.findById(1L);
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void delete() {
        userService.delete(1L);
        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    void save() {
        userService.save(user);
        assertEquals("drowssap", user.getPassword());
        verify(userRepository, times(1)).save(user);
        verify(userRepository, times(1)).flush();
    }

    @Test
    void update() {
        userService.update(user);
        verify(userRepository, times(1)).save(user);
        verify(userRepository, times(1)).flush();
    }
}
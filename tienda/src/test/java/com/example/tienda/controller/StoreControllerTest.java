package com.example.tienda.controller;

import com.example.tienda.model.User;
import com.example.tienda.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class StoreControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private StoreController storeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testRegisterUser() {
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("testPassword");
        user.setEmail("test@example.com");

        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(userService.save(any(User.class))).thenReturn(user);

        ResponseEntity<EntityModel<User>> response = storeController.registerUser(user);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(user, response.getBody().getContent());
    }

    @Test
    void testGetAllUsers() {
        List<User> userList = new ArrayList<>();
        userList.add(new User());
        userList.add(new User());

        when(userService.findAll()).thenReturn(userList);

        ResponseEntity<?> response = storeController.getAllUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userList, response.getBody());
    }

    @Test
    void testGetUserExistingId() {
        Long userId = 1L;
        User user = new User();
        user.setId(userId);

        when(userService.findById(userId)).thenReturn(Optional.of(user));

        ResponseEntity<EntityModel<User>> response = storeController.getUser(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(user, response.getBody().getContent());
    }

    @Test
    void testGetUserNonExistingId() {
        Long userId = 1L;

        when(userService.findById(userId)).thenReturn(Optional.empty());

        ResponseEntity<EntityModel<User>> response = storeController.getUser(userId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
void testUpdateUser() {
    Long userId = 1L;
    User newUser = new User();
    newUser.setUsername("updatedUser");
    newUser.setPassword("updatedPassword");
    newUser.setEmail("updated@example.com");

    User updatedUser = new User();
    updatedUser.setId(userId);
    updatedUser.setUsername("updatedUser");
    updatedUser.setPassword("updatedPassword");
    updatedUser.setEmail("updated@example.com");

    when(userService.update(userId, newUser)).thenReturn(updatedUser);

    ResponseEntity<?> response = storeController.updateUser(userId, newUser);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());
    assertEquals(updatedUser, ((EntityModel<User>) response.getBody()).getContent());
}

@Test
void testDeleteUser() {
    Long userId = 1L;

    ResponseEntity<?> response = storeController.deleteUser(userId);

    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    assertNull(response.getBody());

    verify(userService, times(1)).delete(userId);
}

@Test
void testLoginUserValidCredentials() {
    User loginDetails = new User();
    loginDetails.setUsername("testUser");
    loginDetails.setPassword("testPassword");

    User user = new User();
    user.setUsername("testUser");
    user.setPassword("encodedPassword");

    when(userService.findByUsername("testUser")).thenReturn(Optional.of(user));
    when(passwordEncoder.matches("testPassword", "encodedPassword")).thenReturn(true);

    ResponseEntity<?> response = storeController.loginUser(loginDetails);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("User logged in successfully", response.getBody());
}

@Test
void testLoginUserInvalidCredentials() {
    User loginDetails = new User();
    loginDetails.setUsername("testUser");
    loginDetails.setPassword("testPassword");

    when(userService.findByUsername("testUser")).thenReturn(Optional.empty());

    ResponseEntity<?> response = storeController.loginUser(loginDetails);

    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertEquals("Invalid username or password", response.getBody());
}

}

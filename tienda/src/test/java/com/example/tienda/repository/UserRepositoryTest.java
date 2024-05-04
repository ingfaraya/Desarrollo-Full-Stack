package com.example.tienda.repository;

import com.example.tienda.model.User;
import com.example.tienda.service.UserServiceImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserRepositoryTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void testBuscarUsuarioPorIdExistente() {
        User userMock = new User();
        userMock.setId(1L);
        userMock.setUsername("johndoe");
        userMock.setEmail("johndoe@example.com");

        when(userRepository.findById(1L)).thenReturn(Optional.of(userMock));

        Optional<User> userOptional = userRepository.findById(1L);

        assertEquals(1L, userOptional.get().getId());
        assertEquals("johndoe", userOptional.get().getUsername());
        assertEquals("johndoe@example.com", userOptional.get().getEmail());
    }

    @Test
    public void testBuscarUsuarioPorIdNoExistente() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<User> userOptional = userRepository.findById(1L);

        assertEquals(Optional.empty(), userOptional);
    }
}

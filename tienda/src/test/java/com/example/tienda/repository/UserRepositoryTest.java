package com.example.tienda.repository;

import com.example.tienda.model.User;
import com.example.tienda.repository.UserRepository;
import com.example.tienda.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserRepositoryTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userServiceImpl;

    @Test
    void testFindByUsername() {
        String username = "testUser";
        User user = new User();
        user.setId(1L);
        user.setUsername(username);
        user.setPassword("testPassword");
        user.setEmail("test@example.com");

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

        Optional<User> optionalUser = userServiceImpl.findByUsername(username);

        assertEquals(user, optionalUser.orElse(null));
        verify(userRepository, times(1)).findByUsername(username);
    }

    // Agrega más casos de prueba según sea necesario
}

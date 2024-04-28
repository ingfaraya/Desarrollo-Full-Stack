package com.example.tienda.repository;

import com.example.tienda.model.Address;
import com.example.tienda.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class AddressRepositoryTest {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository; // Agregar el autowired para el repositorio de usuarios

    @Test
    void testFindAllByUserId() {
        // Crear un usuario de ejemplo
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("testPassword");
        user.setEmail("test@example.com");

        // Guardar el usuario en la base de datos
        user = userRepository.save(user);

        // Crear direcciones de ejemplo asociadas al usuario
        Address address1 = new Address();
        address1.setStreet("Street 1");
        address1.setCity("City 1");
        address1.setPostalCode("12345");
        address1.setUser(user);

        Address address2 = new Address();
        address2.setStreet("Street 2");
        address2.setCity("City 2");
        address2.setPostalCode("54321");
        address2.setUser(user);

        // Guardar las direcciones en la base de datos
        addressRepository.save(address1);
        addressRepository.save(address2);

        // Buscar las direcciones por el ID del usuario
        List<Address> addresses = addressRepository.findAllByUserId(user.getId());

        // Verificar que la lista de direcciones no sea nula y contenga las dos direcciones creadas
        assertNotNull(addresses);
        assertEquals(2, addresses.size());
    }

    // Puedes agregar más pruebas según sea necesario para otros métodos del repositorio
}

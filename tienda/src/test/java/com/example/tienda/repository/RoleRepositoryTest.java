package com.example.tienda.repository;

import com.example.tienda.model.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

    @Test
    void testFindByName() {
        // Crear un rol de ejemplo
        Role role = new Role();
        role.setName("ROLE_USER");

        // Guardar el rol en la base de datos
        roleRepository.save(role);

        // Buscar el rol por nombre
        Role foundRole = roleRepository.findByName("ROLE_USER");

        // Verificar que el rol encontrado no sea nulo
        assertNotNull(foundRole);

        // Verificar que el nombre del rol coincida con el nombre buscado
        assertEquals("ROLE_USER", foundRole.getName());
    }

    // Puedes agregar más pruebas según sea necesario para otras consultas personalizadas
}

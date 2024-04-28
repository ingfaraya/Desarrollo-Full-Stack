package com.example.tienda.service;

import com.example.tienda.model.Role;
import com.example.tienda.repository.RoleRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RoleServiceImplTest {

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleServiceImpl roleService;

    public RoleServiceImplTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindById() {
        Long roleId = 1L;
        Role role = new Role();
        role.setId(roleId);

        when(roleRepository.findById(roleId)).thenReturn(Optional.of(role));

        Optional<Role> result = roleService.findById(roleId);

        assertTrue(result.isPresent());
        assertEquals(roleId, result.get().getId());
    }

    @Test
    public void testSave() {
        Role role = new Role();

        when(roleRepository.save(role)).thenReturn(role);

        Role savedRole = roleService.save(role);

        assertNotNull(savedRole);
        assertEquals(role, savedRole);
    }

    // Add more test cases as needed
}

package com.example.tienda.repository;

import com.example.tienda.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);

    // Puedes añadir aquí más consultas personalizadas si es necesario.
}

package com.example.tienda.service;

import com.example.tienda.model.Role;
import java.util.List;
import java.util.Optional;

public interface RoleService {
    List<Role> findAll();
    Optional<Role> findById(Long roleId);
    Role save(Role role);
    Role update(Long roleId, Role role);
    void delete(Long roleId);
    Role findByName(String roleName);
}

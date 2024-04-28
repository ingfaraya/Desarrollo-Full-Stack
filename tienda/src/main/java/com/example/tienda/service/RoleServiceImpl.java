package com.example.tienda.service;

import com.example.tienda.model.Role;
import com.example.tienda.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> findById(Long roleId) {
        return roleRepository.findById(roleId);
    }

    @Override
    @Transactional
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    @Transactional
    public Role update(Long roleId, Role role) {
        return roleRepository.findById(roleId).map(existingRole -> {
            existingRole.setName(role.getName());
            return roleRepository.save(existingRole);
        }).orElseThrow(() -> new RuntimeException("Role not found with id: " + roleId));
    }

    @Override
    @Transactional
    public void delete(Long roleId) {
        roleRepository.deleteById(roleId);
    }

    @Override
    public Role findByName(String roleName) {
        return roleRepository.findByName(roleName);
    }
}

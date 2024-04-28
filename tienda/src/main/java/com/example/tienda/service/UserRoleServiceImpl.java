package com.example.tienda.service;

import com.example.tienda.model.User;
import com.example.tienda.model.Role;
import com.example.tienda.model.UserRole;
import com.example.tienda.repository.UserRoleRepository;
import com.example.tienda.repository.UserRepository;
import com.example.tienda.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserRole addUserRole(Long userId, Long roleId) {
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Role> roleOptional = roleRepository.findById(roleId);

        if (userOptional.isEmpty() || roleOptional.isEmpty()) {
            throw new IllegalArgumentException("User or Role not found");
        }

        User user = userOptional.get();
        Role role = roleOptional.get();
        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);

        return userRoleRepository.save(userRole);
    }

    @Override
    public void removeUserRole(Long userId, Long roleId) {
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Role> roleOptional = roleRepository.findById(roleId);

        if (userOptional.isPresent() && roleOptional.isPresent()) {
            User user = userOptional.get();
            Role role = roleOptional.get();

            userRoleRepository.deleteByUserIdAndRoleId(user.getId(), role.getId());
        } else {
            throw new IllegalArgumentException("User or Role not found");
        }
    }

    @Override
    public List<UserRole> getAllUserRoles() {
        return userRoleRepository.findAll();
    }

    @Override
    public Optional<UserRole> getUserRolesByUserId(Long userId) {
        return userRoleRepository.findAllByUserId(userId);
    }

    @Override
    public Optional<UserRole> getUserRolesByRoleId(Long roleId) {
        return userRoleRepository.findAllByRoleId(roleId);
    }
}

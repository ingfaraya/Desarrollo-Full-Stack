package com.example.tienda.service;

import com.example.tienda.model.UserRole;

import java.util.List;
import java.util.Optional;

public interface UserRoleService {
    UserRole addUserRole(Long userId, Long roleId);

    void removeUserRole(Long userId, Long roleId);

    List<UserRole> getAllUserRoles();

    Optional<UserRole> getUserRolesByUserId(Long userId);

    Optional<UserRole> getUserRolesByRoleId(Long roleId);
}

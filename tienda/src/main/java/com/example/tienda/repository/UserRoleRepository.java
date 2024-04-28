package com.example.tienda.repository;

import com.example.tienda.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    Optional<UserRole> findByUserIdAndRoleId(Long userId, Long roleId);

    Optional<UserRole> findAllByUserId(Long userId);

    Optional<UserRole> findAllByRoleId(Long roleId);

    void deleteByUserIdAndRoleId(Long userId, Long roleId);

}

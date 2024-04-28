package com.example.tienda.service;

import com.example.tienda.model.User;
import com.example.tienda.model.Address;
import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();
    Optional<User> findById(Long id);
    User save(User user);
    User update(Long id, User user);
    void delete(Long id);
    Optional<User> findByUsername(String username);
    List<Address> findAddressesByUserId(Long userId);
    Address addAddress(Long userId, Address address);
    Address updateAddress(Long userId, Long addressId, Address address);
    void deleteAddress(Long userId, Long addressId);
}

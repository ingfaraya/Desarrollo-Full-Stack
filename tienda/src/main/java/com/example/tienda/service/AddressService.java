package com.example.tienda.service;

import com.example.tienda.model.Address;
import java.util.List;
import java.util.Optional;

public interface AddressService {
    List<Address> findAllByUserId(Long userId);
    Optional<Address> findById(Long addressId);
    Address save(Address address);
    Address update(Long addressId, Address address);
    void delete(Long addressId);
}

package com.example.tienda.service;

import com.example.tienda.model.Address;
import com.example.tienda.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public List<Address> findAllByUserId(Long userId) {
        return addressRepository.findAllByUserId(userId);
    }

    @Override
    public Optional<Address> findById(Long addressId) {
        return addressRepository.findById(addressId);
    }

    @Override
    @Transactional
    public Address save(Address address) {
        return addressRepository.save(address);
    }

    @Override
    @Transactional
    public Address update(Long addressId, Address address) {
        return addressRepository.findById(addressId).map(existingAddress -> {
            existingAddress.setStreet(address.getStreet());
            existingAddress.setCity(address.getCity());
            existingAddress.setPostalCode(address.getPostalCode());
            return addressRepository.save(existingAddress);
        }).orElseThrow(() -> new RuntimeException("Address not found with id: " + addressId));
    }

    @Override
    @Transactional
    public void delete(Long addressId) {
        addressRepository.deleteById(addressId);
    }
}

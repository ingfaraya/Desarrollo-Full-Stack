package com.example.tienda.service;

import com.example.tienda.model.Address;
import com.example.tienda.repository.AddressRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AddressServiceImplTest {

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private AddressServiceImpl addressService;

    public AddressServiceImplTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindById() {
        Long addressId = 1L;
        Address address = new Address();
        address.setId(addressId);

        when(addressRepository.findById(addressId)).thenReturn(Optional.of(address));

        Optional<Address> result = addressService.findById(addressId);

        assertTrue(result.isPresent());
        assertEquals(addressId, result.get().getId());
    }

    @Test
    public void testSave() {
        Address address = new Address();

        when(addressRepository.save(address)).thenReturn(address);

        Address savedAddress = addressService.save(address);

        assertNotNull(savedAddress);
        assertEquals(address, savedAddress);
    }

    // Add more test cases as needed
}

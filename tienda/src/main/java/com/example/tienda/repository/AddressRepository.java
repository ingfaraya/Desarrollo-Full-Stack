package com.example.tienda.repository;

import com.example.tienda.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findAllByUserId(Long userId);

    // Si necesitas buscar direcciones por otros criterios, puedes añadir más métodos aquí.
}

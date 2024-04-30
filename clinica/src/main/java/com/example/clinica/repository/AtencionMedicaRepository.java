package com.example.clinica.repository;

import com.example.clinica.model.AtencionMedica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtencionMedicaRepository extends JpaRepository<AtencionMedica, Long> {
    // Puedes añadir métodos adicionales de consulta aquí si es necesario
}

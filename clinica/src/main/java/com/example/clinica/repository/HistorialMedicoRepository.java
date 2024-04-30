package com.example.clinica.repository;

import com.example.clinica.model.HistorialMedico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistorialMedicoRepository extends JpaRepository<HistorialMedico, Long> {
    // Puedes añadir métodos adicionales de consulta aquí si es necesario
}

package com.example.clinica.repository;

import com.example.clinica.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    // Puedes añadir métodos adicionales de consulta aquí si es necesario
}

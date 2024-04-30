package com.example.clinica.service;

import com.example.clinica.model.HistorialMedico;

import java.util.List;
import java.util.Optional;

public interface HistorialMedicoService {
    HistorialMedico guardarHistorialMedico(HistorialMedico historialMedico);

    Optional<HistorialMedico> buscarHistorialMedicoPorId(Long id);

    List<HistorialMedico> buscarTodosLosHistorialesMedicos();

    void eliminarHistorialMedico(Long id);

    HistorialMedico actualizarHistorialMedico(Long id, HistorialMedico nuevoHistorialMedico);
}

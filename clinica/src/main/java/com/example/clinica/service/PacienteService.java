package com.example.clinica.service;

import com.example.clinica.model.Paciente;

import java.util.List;
import java.util.Optional;

public interface PacienteService {
    Paciente guardarPaciente(Paciente paciente);

    Optional<Paciente> buscarPacientePorId(Long id);

    List<Paciente> buscarTodosLosPacientes();

    void eliminarPaciente(Long id);

    List<Paciente> listarPacientes(); // Agregar este método a la interfaz

    Paciente actualizarPaciente(Long id, Paciente nuevoPaciente);
}

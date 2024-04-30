package com.example.clinica.service;

import com.example.clinica.model.Paciente;
import com.example.clinica.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteServiceImpl implements PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Override
    public Paciente guardarPaciente(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    @Override
    public Optional<Paciente> buscarPacientePorId(Long id) {
        return pacienteRepository.findById(id);
    }

    @Override
    public List<Paciente> buscarTodosLosPacientes() {
        return pacienteRepository.findAll();
    }

    @Override
    public void eliminarPaciente(Long id) {
        pacienteRepository.deleteById(id);
    }

    @Override
    public List<Paciente> listarPacientes() {
        return pacienteRepository.findAll(); // Implementación básica, puede necesitar ajustes según requisitos
    }

    @Override
    public Paciente actualizarPaciente(Long id, Paciente nuevoPaciente) {
        Optional<Paciente> pacienteOptional = pacienteRepository.findById(id);
        if (pacienteOptional.isPresent()) {
            Paciente pacienteActual = pacienteOptional.get();
            pacienteActual.setNombre(nuevoPaciente.getNombre());
            pacienteActual.setApellido(nuevoPaciente.getApellido());
            pacienteActual.setFechaNacimiento(nuevoPaciente.getFechaNacimiento());
            // Actualizar otros campos según sea necesario
            return pacienteRepository.save(pacienteActual);
        } else {
            throw new RuntimeException("Paciente no encontrado con ID: " + id);
        }
    }
}

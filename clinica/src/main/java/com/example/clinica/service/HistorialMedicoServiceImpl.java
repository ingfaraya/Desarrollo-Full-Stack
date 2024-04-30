package com.example.clinica.service;

import com.example.clinica.model.HistorialMedico;
import com.example.clinica.repository.HistorialMedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HistorialMedicoServiceImpl implements HistorialMedicoService {

    private final HistorialMedicoRepository historialMedicoRepository;

    @Autowired
    public HistorialMedicoServiceImpl(HistorialMedicoRepository historialMedicoRepository) {
        this.historialMedicoRepository = historialMedicoRepository;
    }

    @Override
    public HistorialMedico guardarHistorialMedico(HistorialMedico historialMedico) {
        return historialMedicoRepository.save(historialMedico);
    }

    @Override
    public Optional<HistorialMedico> buscarHistorialMedicoPorId(Long id) {
        return historialMedicoRepository.findById(id);
    }

    @Override
    public List<HistorialMedico> buscarTodosLosHistorialesMedicos() {
        return historialMedicoRepository.findAll();
    }

    @Override
    public void eliminarHistorialMedico(Long id) {
        historialMedicoRepository.deleteById(id);
    }

    @Override
    public HistorialMedico actualizarHistorialMedico(Long id, HistorialMedico nuevoHistorialMedico) {
        return historialMedicoRepository.findById(id)
                .map(historialExistente -> {
                    historialExistente.setMotivoConsulta(nuevoHistorialMedico.getMotivoConsulta());
                    historialExistente.setDiagnostico(nuevoHistorialMedico.getDiagnostico());
                    historialExistente.setTratamiento(nuevoHistorialMedico.getTratamiento());
                    return historialMedicoRepository.save(historialExistente);
                })
                .orElseThrow(() -> new IllegalArgumentException("El historial médico con el ID " + id + " no existe"));
    }

}

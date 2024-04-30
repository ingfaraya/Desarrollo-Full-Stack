package com.example.clinica.service;

import com.example.clinica.model.AtencionMedica;
import com.example.clinica.repository.AtencionMedicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AtencionMedicaServiceImpl implements AtencionMedicaService {

    @Autowired
    private AtencionMedicaRepository atencionMedicaRepository;

    @Override
    public AtencionMedica guardarAtencionMedica(AtencionMedica atencionMedica) {
        return atencionMedicaRepository.save(atencionMedica);
    }

    @Override
    public Optional<AtencionMedica> buscarAtencionMedicaPorId(Long id) {
        return atencionMedicaRepository.findById(id);
    }

    @Override
    public List<AtencionMedica> buscarTodasLasAtencionesMedicas() {
        return atencionMedicaRepository.findAll();
    }

    @Override
    public void eliminarAtencionMedica(Long id) {
        atencionMedicaRepository.deleteById(id);
    }

    @Override
    public AtencionMedica actualizarAtencionMedica(Long id, AtencionMedica nuevaAtencionMedica) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actualizarAtencionMedica'");
    }
}

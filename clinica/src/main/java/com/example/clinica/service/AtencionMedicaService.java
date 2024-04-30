package com.example.clinica.service;

import com.example.clinica.model.AtencionMedica;

import java.util.List;
import java.util.Optional;

public interface AtencionMedicaService {
    AtencionMedica guardarAtencionMedica(AtencionMedica atencionMedica);

    Optional<AtencionMedica> buscarAtencionMedicaPorId(Long id);

    List<AtencionMedica> buscarTodasLasAtencionesMedicas();

    void eliminarAtencionMedica(Long id);

    AtencionMedica actualizarAtencionMedica(Long id, AtencionMedica nuevaAtencionMedica);
}

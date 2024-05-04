package com.example.clinica.service;

import com.example.clinica.model.AtencionMedica;
import com.example.clinica.repository.AtencionMedicaRepository;
import com.example.clinica.service.AtencionMedicaService;
import com.example.clinica.service.AtencionMedicaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AtencionMedicaServiceImplTest {

    @Mock
    private AtencionMedicaRepository atencionMedicaRepository;

    @InjectMocks
    private AtencionMedicaService atencionMedicaService = new AtencionMedicaServiceImpl();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void guardarAtencionMedica_AtencionMedicaValida_DebeRetornarAtencionMedicaGuardada() {
        AtencionMedica atencionMedica = new AtencionMedica();
        atencionMedica.setId(1L);
        atencionMedica.setDescripcion("Consulta de rutina");

        when(atencionMedicaRepository.save(atencionMedica)).thenReturn(atencionMedica);

        AtencionMedica atencionMedicaGuardada = atencionMedicaService.guardarAtencionMedica(atencionMedica);

        assertEquals(1L, atencionMedicaGuardada.getId());
        assertEquals("Consulta de rutina", atencionMedicaGuardada.getDescripcion());

        verify(atencionMedicaRepository, times(1)).save(atencionMedica);
    }

    @Test
    public void buscarAtencionMedicaPorId_AtencionMedicaExistente_DebeRetornarAtencionMedicaOptional() {
        AtencionMedica atencionMedica = new AtencionMedica();
        atencionMedica.setId(1L);
        atencionMedica.setDescripcion("Consulta de rutina");

        when(atencionMedicaRepository.findById(1L)).thenReturn(Optional.of(atencionMedica));

        Optional<AtencionMedica> atencionMedicaOptional = atencionMedicaService.buscarAtencionMedicaPorId(1L);

        assertEquals(1L, atencionMedicaOptional.get().getId());
        assertEquals("Consulta de rutina", atencionMedicaOptional.get().getDescripcion());

        verify(atencionMedicaRepository, times(1)).findById(1L);
    }

    @Test
    public void buscarAtencionMedicaPorId_AtencionMedicaNoExistente_DebeRetornarOptionalVacio() {
        when(atencionMedicaRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<AtencionMedica> atencionMedicaOptional = atencionMedicaService.buscarAtencionMedicaPorId(1L);

        assertEquals(Optional.empty(), atencionMedicaOptional);

        verify(atencionMedicaRepository, times(1)).findById(1L);
    }

    @Test
    public void buscarTodasLasAtencionesMedicas_ListaVacia_DebeRetornarListaVacia() {
        when(atencionMedicaRepository.findAll()).thenReturn(Collections.emptyList());

        List<AtencionMedica> atencionesMedicas = atencionMedicaService.buscarTodasLasAtencionesMedicas();

        assertEquals(0, atencionesMedicas.size());

        verify(atencionMedicaRepository, times(1)).findAll();
    }

    @Test
    public void eliminarAtencionMedica_AtencionMedicaExistente_DebeEliminarAtencionMedica() {
        atencionMedicaService.eliminarAtencionMedica(1L);

        verify(atencionMedicaRepository, times(1)).deleteById(1L);
    }
}

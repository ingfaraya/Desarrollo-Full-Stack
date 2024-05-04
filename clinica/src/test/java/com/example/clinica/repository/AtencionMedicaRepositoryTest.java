package com.example.clinica.repository;

import com.example.clinica.model.AtencionMedica;
import com.example.clinica.repository.AtencionMedicaRepository;
import com.example.clinica.service.AtencionMedicaServiceImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AtencionMedicaRepositoryTest {

    @Mock
    private AtencionMedicaRepository atencionMedicaRepository;

    @InjectMocks
    private AtencionMedicaServiceImpl atencionMedicaService;

    @Test
    public void testBuscarAtencionMedicaPorIdExistente() {
        AtencionMedica atencionMedicaMock = new AtencionMedica();
        atencionMedicaMock.setId(1L);
        atencionMedicaMock.setDescripcion("Consulta de rutina");

        when(atencionMedicaRepository.findById(1L)).thenReturn(Optional.of(atencionMedicaMock));

        Optional<AtencionMedica> atencionMedicaOptional = atencionMedicaRepository.findById(1L);

        assertEquals(1L, atencionMedicaOptional.get().getId());
        assertEquals("Consulta de rutina", atencionMedicaOptional.get().getDescripcion());
    }

    @Test
    public void testBuscarAtencionMedicaPorIdNoExistente() {
        when(atencionMedicaRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<AtencionMedica> atencionMedicaOptional = atencionMedicaRepository.findById(1L);

        assertEquals(Optional.empty(), atencionMedicaOptional);
    }
}

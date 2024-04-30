package com.example.clinica.service;

import com.example.clinica.model.Paciente;
import com.example.clinica.repository.PacienteRepository;
import com.example.clinica.service.PacienteService;
import com.example.clinica.service.PacienteServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class PacienteServiceImplTest {

    @Mock
    private PacienteRepository pacienteRepository;

    @InjectMocks
    private PacienteService pacienteService = new PacienteServiceImpl();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void guardarPaciente_PacienteValido_DebeRetornarPacienteGuardado() {
        Paciente paciente = new Paciente();
        paciente.setId(1L);
        paciente.setNombre("John Doe");

        when(pacienteRepository.save(paciente)).thenReturn(paciente);

        Paciente pacienteGuardado = pacienteService.guardarPaciente(paciente);

        assertEquals(1L, pacienteGuardado.getId());
        assertEquals("John Doe", pacienteGuardado.getNombre());

        verify(pacienteRepository, times(1)).save(paciente);
    }

    @Test
    public void buscarPacientePorId_PacienteExistente_DebeRetornarPacienteOptional() {
        Paciente paciente = new Paciente();
        paciente.setId(1L);
        paciente.setNombre("John Doe");

        when(pacienteRepository.findById(1L)).thenReturn(Optional.of(paciente));

        Optional<Paciente> pacienteOptional = pacienteService.buscarPacientePorId(1L);

        assertEquals(1L, pacienteOptional.get().getId());
        assertEquals("John Doe", pacienteOptional.get().getNombre());

        verify(pacienteRepository, times(1)).findById(1L);
    }

    @Test
    public void buscarPacientePorId_PacienteNoExistente_DebeRetornarOptionalVacio() {
        when(pacienteRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Paciente> pacienteOptional = pacienteService.buscarPacientePorId(1L);

        assertEquals(Optional.empty(), pacienteOptional);

        verify(pacienteRepository, times(1)).findById(1L);
    }

    @Test
    public void buscarTodosLosPacientes_ListaVacia_DebeRetornarListaVacia() {
        when(pacienteRepository.findAll()).thenReturn(Collections.emptyList());

        List<Paciente> pacientes = pacienteService.buscarTodosLosPacientes();

        assertEquals(0, pacientes.size());

        verify(pacienteRepository, times(1)).findAll();
    }

    @Test
    public void eliminarPaciente_PacienteExistente_DebeEliminarPaciente() {
        pacienteService.eliminarPaciente(1L);

        verify(pacienteRepository, times(1)).deleteById(1L);
    }

    @Test
    public void listarPacientes_ListaVacia_DebeRetornarListaVacia() {
        when(pacienteRepository.findAll()).thenReturn(Collections.emptyList());

        List<Paciente> pacientes = pacienteService.listarPacientes();

        assertEquals(0, pacientes.size());

        verify(pacienteRepository, times(1)).findAll();
    }

    @Test
    public void actualizarPaciente_PacienteExistente_DebeActualizarPaciente() {
        Paciente pacienteExistente = new Paciente();
        pacienteExistente.setId(1L);
        pacienteExistente.setNombre("John Doe");

        Paciente pacienteActualizado = new Paciente();
        pacienteActualizado.setId(1L);
        pacienteActualizado.setNombre("Jane Doe");

        when(pacienteRepository.findById(1L)).thenReturn(Optional.of(pacienteExistente));
        when(pacienteRepository.save(pacienteExistente)).thenReturn(pacienteActualizado);

        Paciente paciente = pacienteService.actualizarPaciente(1L, pacienteActualizado);

        assertEquals(1L, paciente.getId());
        assertEquals("Jane Doe", paciente.getNombre());

        verify(pacienteRepository, times(1)).findById(1L);
        verify(pacienteRepository, times(1)).save(pacienteExistente);
    }

    @Test
    public void actualizarPaciente_PacienteNoExistente_DebeLanzarExcepcion() {
        Paciente pacienteNoExistente = new Paciente();
        pacienteNoExistente.setId(1L);
        pacienteNoExistente.setNombre("John Doe");

        when(pacienteRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> pacienteService.actualizarPaciente(1L, pacienteNoExistente));

        verify(pacienteRepository, times(1)).findById(1L);
        verify(pacienteRepository, never()).save(any());
    }
}

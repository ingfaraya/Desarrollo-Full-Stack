package com.example.clinica.controller;

import com.example.clinica.controller.ClinicaController;
import com.example.clinica.model.Paciente;
import com.example.clinica.service.PacienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ClinicaControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PacienteService pacienteService;

    @InjectMocks
    private ClinicaController clinicaController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(clinicaController).build();
    }

    @Test
    public void buscarPacientePorId_PacienteExistente_DebeRetornarPaciente() throws Exception {
        Paciente paciente = new Paciente();
        paciente.setId(1L);
        paciente.setNombre("John Doe");

        when(pacienteService.buscarPacientePorId(1L)).thenReturn(Optional.of(paciente));

        mockMvc.perform(get("/clinica/pacientes/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.nombre").value("John Doe"));
    }

    @Test
    public void buscarPacientePorId_PacienteNoExistente_DebeRetornarNotFound() throws Exception {
        when(pacienteService.buscarPacientePorId(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/clinica/pacientes/1"))
                .andExpect(status().isNotFound());
    }

}

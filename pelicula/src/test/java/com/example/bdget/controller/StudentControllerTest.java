package com.example.pelicula.controller;
/*
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.EntityModel;
import org.springframework.test.web.servlet.MockMvc;
import com.example.pelicula.model.Pelicula;
import com.example.pelicula.service.PeliculaService;

@WebMvcTest(PeliculaController.class)
public class PeliculaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PeliculaService studentServiceMock;

    @Test
    public void obtenerTodosTest() throws Exception {
        Pelicula estudiante1 = new Pelicula();
        estudiante1.setName("John");
        estudiante1.setId(1L);
        Pelicula estudiante2 = new Pelicula();
        estudiante2.setName("Doe");
        estudiante2.setId(2L);
        List<Pelicula> estudiantes = List.of(estudiante1, estudiante2);

        List<EntityModel<Pelicula>> peliculasResources = estudiantes.stream()
            .map(student -> EntityModel.of(student))
            .collect(Collectors.toList());

        when(studentServiceMock.getAllPeliculas()).thenReturn(estudiantes);

        mockMvc.perform(get("/peliculas"))
                .andExpect(status().isOk())
                // Here, use direct JSON path matching without Matchers
                .andExpect(jsonPath("$._embedded.peliculas.length()").value(2))
                .andExpect(jsonPath("$._embedded.peliculas[0].name").value("John"))
                .andExpect(jsonPath("$._embedded.peliculas[1].name").value("Doe"))
                .andExpect(jsonPath("$._embedded.peliculas[0]._links.self.href").value("http://localhost:8080/peliculas/1"))
                .andExpect(jsonPath("$._embedded.peliculas[1]._links.self.href").value("http://localhost:8080/peliculas/2"));
    }
}
*/
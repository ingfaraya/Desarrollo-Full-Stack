package com.example.pelicula.controller;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.http.MediaType.*;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(PeliculaController.class)
public class PeliculaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PeliculaService peliculaService;

    @Test
    void createPeliculaTest() throws Exception {
        Pelicula nuevaPelicula = new Pelicula();
        nuevaPelicula.setTitulo("Inception");
        nuevaPelicula.setAno("2010");
        nuevaPelicula.setDirector("Christopher Nolan");
        nuevaPelicula.setGenero("Ciencia Ficción");
        nuevaPelicula.setSinopsis(
                "Un ladrón que roba secretos corporativos a través del uso de la tecnología de compartir sueños...");

        Pelicula peliculaGuardada = new Pelicula();
        peliculaGuardada.setId(1L);
        peliculaGuardada.setTitulo("Inception");
        peliculaGuardada.setAno("2010");
        peliculaGuardada.setDirector("Christopher Nolan");
        peliculaGuardada.setGenero("Ciencia Ficción");
        peliculaGuardada.setSinopsis(
                "Un ladrón que roba secretos corporativos a través del uso de la tecnología de compartir sueños...");

        given(peliculaService.createPelicula(any(Pelicula.class))).willReturn(peliculaGuardada);

        mockMvc.perform(post("/peliculas")
                .contentType(APPLICATION_JSON)
                .content(
                        "{\"titulo\":\"Inception\",\"ano\":\"2010\",\"director\":\"Christopher Nolan\",\"genero\":\"Ciencia Ficción\",\"sinopsis\":\"Un ladrón que roba secretos corporativos a través del uso de la tecnología de compartir sueños...\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Inception"))
                .andExpect(jsonPath("$.ano").value("2010"))
                .andExpect(jsonPath("$.director").value("Christopher Nolan"))
                .andExpect(jsonPath("$.genero").value("Ciencia Ficción"))
                .andExpect(jsonPath("$.sinopsis").value(
                        "Un ladrón que roba secretos corporativos a través del uso de la tecnología de compartir sueños..."))
                .andExpect(jsonPath("$._links.self.href").exists())
                .andExpect(jsonPath("$._links.all-peliculas.href").exists());
    }

    @Test
    void getAllPeliculasSuccessTest() throws Exception {
        List<Pelicula> peliculas = Arrays.asList(
                new Pelicula(1L, "Inception", "2010", "Christopher Nolan", "Ciencia Ficción",
                        "Un ladrón que roba secretos..."),
                new Pelicula(2L, "Interstellar", "2014", "Christopher Nolan", "Aventura Espacial",
                        "Un grupo de exploradores..."));

        given(peliculaService.getAllPeliculas()).willReturn(peliculas);

        mockMvc.perform(get("/peliculas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.peliculas[0].titulo").value("Inception"))
                .andExpect(jsonPath("$._embedded.peliculas[1].titulo").value("Interstellar"));
    }

    @Test
    void getAllPeliculasEmptyTest() throws Exception {
        given(peliculaService.getAllPeliculas()).willReturn(Collections.emptyList());

        mockMvc.perform(get("/peliculas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.peliculas").doesNotExist());
    }

    @Test
    void getPeliculaByIdSuccessTest() throws Exception {
        Pelicula pelicula = new Pelicula(1L, "Inception", "2010", "Christopher Nolan", "Ciencia Ficción",
                "Un ladrón que roba secretos...");
        given(peliculaService.getPeliculaById(1L)).willReturn(Optional.of(pelicula));

        mockMvc.perform(get("/peliculas/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Inception"));
    }

    @Test
    void getPeliculaByIdNotFoundTest() throws Exception {
        given(peliculaService.getPeliculaById(99L)).willReturn(Optional.empty());

        mockMvc.perform(get("/peliculas/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void createPeliculaSuccessTest() throws Exception {
        Pelicula pelicula = new Pelicula(1L, "Inception", "2010", "Christopher Nolan", "Ciencia Ficción",
                "Un ladrón que roba secretos...");
        given(peliculaService.createPelicula(any(Pelicula.class))).willReturn(pelicula);

        mockMvc.perform(post("/peliculas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                        "{\"titulo\":\"Inception\",\"ano\":\"2010\",\"director\":\"Christopher Nolan\",\"genero\":\"Ciencia Ficción\",\"sinopsis\":\"Un ladrón que roba secretos...\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Inception"));
    }

    @Test
    void createPeliculaValidationFailTest() throws Exception {
        mockMvc.perform(post("/peliculas")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"titulo\":\"\",\"ano\":\"\",\"director\":\"\",\"genero\":\"\",\"sinopsis\":\"\"}")) // Empty
                                                                                                                // fields
                .andExpect(status().isBadRequest());
    }

    @Test
    void updatePeliculaSuccessTest() throws Exception {
        Pelicula updatedPelicula = new Pelicula(1L, "Inception Updated", "2011", "Christopher Nolan", "Thriller",
                "Updated sinopsis...");
        given(peliculaService.updatePelicula(eq(1L), any(Pelicula.class))).willReturn(updatedPelicula);

        mockMvc.perform(put("/peliculas/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                        "{\"titulo\":\"Inception Updated\",\"ano\":\"2011\",\"director\":\"Christopher Nolan\",\"genero\":\"Thriller\",\"sinopsis\":\"Updated sinopsis...\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Inception Updated"));
    }

    @Test
    void updatePeliculaNotFoundTest() throws Exception {
        given(peliculaService.updatePelicula(eq(99L), any(Pelicula.class)))
                .willThrow(new PeliculaNotFoundException("Pelicula not found with id: 99"));

        mockMvc.perform(put("/peliculas/99")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                        "{\"titulo\":\"Inception\",\"ano\":\"2010\",\"director\":\"Christopher Nolan\",\"genero\":\"Ciencia Ficción\",\"sinopsis\":\"Un ladrón que roba secretos...\"}"))
                .andExpect(status().isNotFound());
    }

    @Test
    void deletePeliculaSuccessTest() throws Exception {
        willDoNothing().given(peliculaService).deletePelicula(1L);

        mockMvc.perform(delete("/peliculas/1"))
                .andExpect(status().isOk());
    }

    @Test
    void deletePeliculaNotFoundTest() throws Exception {
        doThrow(new PeliculaNotFoundException("Pelicula not found with id: 99")).when(peliculaService)
                .deletePelicula(99L);

        mockMvc.perform(delete("/peliculas/99"))
                .andExpect(status().isNotFound());
    }

}

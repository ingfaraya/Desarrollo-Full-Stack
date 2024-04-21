package com.example.pelicula.controller;

import com.example.pelicula.model.Pelicula;
import com.example.pelicula.service.PeliculaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PeliculaController.class)
public class PeliculaControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private PeliculaService peliculaService;

        @Test
        public void testGetAllPeliculas() throws Exception {
                Pelicula pelicula = new Pelicula();
                pelicula.setId(1L);
                pelicula.setTitulo("Inception");
                pelicula.setAno("2010");
                pelicula.setDirector("Christopher Nolan");
                pelicula.setGenero("Ciencia Ficción");
                pelicula.setSinopsis("A thief who steals...");

                List<EntityModel<Pelicula>> peliculaModels = Arrays.asList(EntityModel.of(pelicula,
                                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PeliculaController.class)
                                                .getPeliculaById(pelicula.getId())).withSelfRel()));

                CollectionModel<EntityModel<Pelicula>> collectionModel = CollectionModel.of(peliculaModels,
                                WebMvcLinkBuilder.linkTo(
                                                WebMvcLinkBuilder.methodOn(PeliculaController.class).getAllPeliculas())
                                                .withSelfRel());

                when(peliculaService.getAllPeliculas()).thenReturn(Arrays.asList(pelicula));
                when(peliculaService.getPeliculaById(any())).thenReturn(Optional.of(pelicula));

                mockMvc.perform(get("/peliculas"))
                                .andExpect(status().isOk());
        }

        @Test
        public void testGetPeliculaById() throws Exception {
                Pelicula pelicula = new Pelicula();
                pelicula.setId(1L);
                pelicula.setTitulo("Inception");

                when(peliculaService.getPeliculaById(1L)).thenReturn(Optional.of(pelicula));

                mockMvc.perform(get("/peliculas/1"))
                                .andExpect(status().isOk());
        }

        @Test
        public void testCreatePelicula() throws Exception {
                Pelicula pelicula = new Pelicula();
                pelicula.setId(1L);
                pelicula.setTitulo("Inception");

                when(peliculaService.createPelicula(any(Pelicula.class))).thenReturn(pelicula);

                mockMvc.perform(post("/peliculas")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"titulo\":\"Inception\",\"ano\":\"2010\",\"director\":\"Christopher Nolan\",\"genero\":\"Ciencia Ficción\",\"sinopsis\":\"A thief who steals...\"}"))
                                .andExpect(status().isOk());
        }

        @Test
        public void testUpdatePelicula() throws Exception {
                Pelicula pelicula = new Pelicula();
                pelicula.setId(1L);
                pelicula.setTitulo("Inception Updated");

                when(peliculaService.updatePelicula(eq(1L), any(Pelicula.class))).thenReturn(pelicula);

                mockMvc.perform(put("/peliculas/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"titulo\":\"Inception Updated\",\"ano\":\"2010\",\"director\":\"Christopher Nolan\",\"genero\":\"Ciencia Ficción\",\"sinopsis\":\"A thief who steals...\"}"))
                                .andExpect(status().isOk());
        }

        @Test
        public void testDeletePelicula() throws Exception {
                doNothing().when(peliculaService).deletePelicula(1L);

                mockMvc.perform(delete("/peliculas/1"))
                                .andExpect(status().isOk());
        }
}

package com.example.pelicula.service;

import com.example.pelicula.model.Pelicula;
import com.example.pelicula.repository.PeliculaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

/*
Descripción de las Pruebas
testGetAllPeliculas: Verifica que el servicio pueda recuperar y retornar todas las películas.
testGetPeliculaById: Verifica que el servicio pueda recuperar una película por su ID.
testCreatePelicula: Verifica que el servicio pueda crear una nueva película.
testUpdatePelicula: Verifica que el servicio pueda actualizar una película existente.
testDeletePelicula: Verifica que el servicio maneje correctamente la eliminación de una película.
*/

@SpringBootTest
public class PeliculaServiceTest {

    @Mock
    private PeliculaRepository peliculaRepository;

    @InjectMocks
    private PeliculaService peliculaService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllPeliculas() {
        List<Pelicula> peliculas = Arrays.asList(
            new Pelicula(1L, "Inception", "2010", "Christopher Nolan", "Ciencia Ficción", "Un ladrón..."),
            new Pelicula(2L, "Interstellar", "2014", "Christopher Nolan", "Aventura Espacial", "Un grupo de exploradores...")
        );
        when(peliculaRepository.findAll()).thenReturn(peliculas);

        List<Pelicula> result = peliculaService.getAllPeliculas();
        assertEquals(2, result.size());
        assertEquals("Inception", result.get(0).getTitulo());
        assertEquals("Interstellar", result.get(1).getTitulo());
    }

    @Test
    void testGetPeliculaById() {
        Optional<Pelicula> pelicula = Optional.of(new Pelicula(1L, "Inception", "2010", "Christopher Nolan", "Ciencia Ficción", "Un ladrón..."));
        when(peliculaRepository.findById(1L)).thenReturn(pelicula);

        Optional<Pelicula> result = peliculaService.getPeliculaById(1L);
        assertTrue(result.isPresent());
        assertEquals("Inception", result.get().getTitulo());
    }

    @Test
    void testCreatePelicula() {
        Pelicula pelicula = new Pelicula(null, "Inception", "2010", "Christopher Nolan", "Ciencia Ficción", "Un ladrón...");
        when(peliculaRepository.save(any(Pelicula.class))).thenReturn(pelicula);

        Pelicula savedPelicula = peliculaService.createPelicula(pelicula);
        assertNotNull(savedPelicula);
        assertEquals("Inception", savedPelicula.getTitulo());
    }

    @Test
    void testUpdatePelicula() {
        Pelicula pelicula = new Pelicula(1L, "Inception", "2010", "Christopher Nolan", "Ciencia Ficción", "Un ladrón...");
        when(peliculaRepository.save(pelicula)).thenReturn(pelicula);

        Pelicula updatedPelicula = peliculaService.updatePelicula(1L, pelicula);
        assertNotNull(updatedPelicula);
        assertEquals(1L, updatedPelicula.getId());
        assertEquals("Inception", updatedPelicula.getTitulo());
    }

    @Test
    void testDeletePelicula() {
        doNothing().when(peliculaRepository).deleteById(1L);
        peliculaService.deletePelicula(1L);
        verify(peliculaRepository, times(1)).deleteById(1L);
    }
}

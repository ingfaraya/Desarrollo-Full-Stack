package com.example.pelicula.service;

import com.example.pelicula.model.Pelicula;
import com.example.pelicula.repository.PeliculaRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class PeliculaServiceTest {

    @Mock
    private PeliculaRepository peliculaRepository;

    @InjectMocks
    private PeliculaServiceImpl peliculaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnAllPeliculas() {
        Pelicula pelicula1 = new Pelicula();
        pelicula1.setTitulo("Inception");
        Pelicula pelicula2 = new Pelicula();
        pelicula2.setTitulo("Interstellar");

        when(peliculaRepository.findAll()).thenReturn(Arrays.asList(pelicula1, pelicula2));

        List<Pelicula> peliculas = peliculaService.getAllPeliculas();
        assertEquals(2, peliculas.size());
        verify(peliculaRepository).findAll();
    }

    @Test
    void shouldReturnPeliculaById() {
        Pelicula pelicula = new Pelicula();
        pelicula.setId(1L);
        pelicula.setTitulo("Inception");
        when(peliculaRepository.findById(1L)).thenReturn(Optional.of(pelicula));

        Optional<Pelicula> result = peliculaService.getPeliculaById(1L);
        assertTrue(result.isPresent());
        assertEquals("Inception", result.get().getTitulo());
    }

    @Test
    void shouldCreatePelicula() {
        Pelicula pelicula = new Pelicula();
        pelicula.setTitulo("Inception");
        when(peliculaRepository.save(pelicula)).thenReturn(pelicula);

        Pelicula savedPelicula = peliculaService.createPelicula(pelicula);
        assertNotNull(savedPelicula);
        assertEquals("Inception", savedPelicula.getTitulo());
    }

    @Test
    void shouldUpdatePelicula() {
        Long peliculaId = 1L;
        Pelicula existingPelicula = new Pelicula();
        existingPelicula.setId(peliculaId);
        existingPelicula.setTitulo("Inception");
        existingPelicula.setAno("2010");
        existingPelicula.setDirector("Christopher Nolan");
        existingPelicula.setGenero("Ciencia Ficción");
        existingPelicula.setSinopsis("A thief who steals...");

        // Mockeo para asegurar que la película existe
        when(peliculaRepository.existsById(peliculaId)).thenReturn(true);
        when(peliculaRepository.findById(peliculaId)).thenReturn(Optional.of(existingPelicula));
        when(peliculaRepository.save(any(Pelicula.class))).thenReturn(existingPelicula);

        // Llamada al método de servicio
        Pelicula updatedPelicula = peliculaService.updatePelicula(peliculaId, existingPelicula);

        assertNotNull(updatedPelicula);
        assertEquals("Inception", updatedPelicula.getTitulo());

        // Verifica que los métodos del repositorio se llamaron
        verify(peliculaRepository).findById(peliculaId);
        verify(peliculaRepository).save(existingPelicula);
    }

    @Test
    void shouldDeletePelicula() {
        doNothing().when(peliculaRepository).deleteById(1L);
        peliculaService.deletePelicula(1L);
        verify(peliculaRepository).deleteById(1L);
    }
}

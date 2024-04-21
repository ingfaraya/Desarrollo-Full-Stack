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

@SpringBootTest
public class PeliculaServiceImplTest {

    @Mock
    private PeliculaRepository peliculaRepository;

    @InjectMocks
    private PeliculaServiceImpl peliculaService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllPeliculasTest() {
        Pelicula pelicula1 = new Pelicula();
        pelicula1.setId(1L);
        pelicula1.setTitulo("Inception");
        pelicula1.setAno("2010");
        pelicula1.setDirector("Christopher Nolan");
        pelicula1.setGenero("Ciencia Ficción");
        pelicula1.setSinopsis("Un ladrón...");

        Pelicula pelicula2 = new Pelicula();
        pelicula2.setId(2L);
        pelicula2.setTitulo("Interstellar");
        pelicula2.setAno("2014");
        pelicula2.setDirector("Christopher Nolan");
        pelicula2.setGenero("Aventura Espacial");
        pelicula2.setSinopsis("Un grupo de exploradores...");

        when(peliculaRepository.findAll()).thenReturn(Arrays.asList(pelicula1, pelicula2));

        List<Pelicula> peliculas = peliculaService.getAllPeliculas();
        assertFalse(peliculas.isEmpty());
        assertEquals(2, peliculas.size());
        assertEquals("Inception", peliculas.get(0).getTitulo());
    }

    @Test
    void getPeliculaByIdTest() {
        Pelicula pelicula = new Pelicula();
        pelicula.setId(1L);
        pelicula.setTitulo("Inception");
        pelicula.setAno("2010");
        pelicula.setDirector("Christopher Nolan");
        pelicula.setGenero("Ciencia Ficción");
        pelicula.setSinopsis("Un ladrón...");

        when(peliculaRepository.findById(1L)).thenReturn(Optional.of(pelicula));

        Optional<Pelicula> found = peliculaService.getPeliculaById(1L);
        assertTrue(found.isPresent());
        assertEquals("Inception", found.get().getTitulo());
    }

    @Test
    void createPeliculaTest() {
        Pelicula pelicula = new Pelicula();
        pelicula.setTitulo("Inception");
        pelicula.setAno("2010");
        pelicula.setDirector("Christopher Nolan");
        pelicula.setGenero("Ciencia Ficción");
        pelicula.setSinopsis("Un ladrón...");

        when(peliculaRepository.save(pelicula)).thenReturn(pelicula);

        Pelicula saved = peliculaService.createPelicula(pelicula);
        assertNotNull(saved);
        assertEquals("Inception", saved.getTitulo());
    }

    @Test
    void updatePeliculaTest() {
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
    void updateNonExistentPeliculaTest() {
        Long peliculaId = 1L;
        Pelicula pelicula = new Pelicula();

        // Configurar para que no se encuentre la película
        when(peliculaRepository.existsById(peliculaId)).thenReturn(false);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            peliculaService.updatePelicula(peliculaId, pelicula);
        });

        assertEquals("Pelicula not found with id: 1", exception.getMessage());
    }

    @Test
    void deletePeliculaTest() {
        doNothing().when(peliculaRepository).deleteById(1L);

        peliculaService.deletePelicula(1L);
        verify(peliculaRepository, times(1)).deleteById(1L);
    }
}

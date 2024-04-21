package com.example.pelicula.repository;

import com.example.pelicula.model.Pelicula;

import jakarta.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PeliculaRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PeliculaRepository peliculaRepository;

    @Test
    public void testFindById() {
        // Given
        Pelicula pelicula = new Pelicula();
        pelicula.setTitulo("Inception");
        pelicula.setAno("2010");
        pelicula.setDirector("Christopher Nolan");
        pelicula.setGenero("Ciencia Ficción");
        pelicula.setSinopsis("Un ladrón que...");
        pelicula = entityManager.persistFlushFind(pelicula);

        // When
        Optional<Pelicula> found = peliculaRepository.findById(pelicula.getId());

        // Then
        assertTrue(found.isPresent());
        assertEquals("Inception", found.get().getTitulo());
    }

    @Test
    public void testSave() {
        // Given
        Pelicula pelicula = new Pelicula();
        pelicula.setTitulo("Interstellar");
        pelicula.setAno("2014");
        pelicula.setDirector("Christopher Nolan");
        pelicula.setGenero("Aventura Espacial");
        pelicula.setSinopsis("Aventuras en el espacio...");

        // When
        Pelicula saved = peliculaRepository.save(pelicula);

        // Then
        assertNotNull(saved.getId());
        assertEquals("Interstellar", saved.getTitulo());
    }

    @Test
    public void testDelete() {
        // Given
        Pelicula pelicula = new Pelicula();
        pelicula.setTitulo("Inception");
        pelicula.setAno("2014");
        pelicula.setDirector("Christopher Nolan");
        pelicula.setGenero("Aventura Espacial");
        pelicula.setSinopsis("Aventuras en el espacio...");
        pelicula = entityManager.persistFlushFind(pelicula);

        // When
        peliculaRepository.delete(pelicula);

        // Then
        Optional<Pelicula> deleted = peliculaRepository.findById(pelicula.getId());
        assertFalse(deleted.isPresent());
    }

    @Test
    public void testFindAll() {
        // Given
        Pelicula pelicula1 = new Pelicula();
        pelicula1.setTitulo("Inception");
        pelicula1.setAno("2014");
        pelicula1.setDirector("Christopher Nolan");
        pelicula1.setGenero("Aventura Espacial");
        pelicula1.setSinopsis("Aventuras en el espacio...");
        entityManager.persist(pelicula1);

        Pelicula pelicula2 = new Pelicula();
        pelicula2.setTitulo("Interstellar");
        pelicula2.setAno("2014");
        pelicula2.setDirector("Christopher Nolan");
        pelicula2.setGenero("Aventura Espacial");
        pelicula2.setSinopsis("Aventuras en el espacio...");
        entityManager.persist(pelicula2);

        entityManager.flush();

        // When
        List<Pelicula> peliculas = peliculaRepository.findAll();

        // Then
        assertEquals(9, peliculas.size());
    }

    @Test
    public void testSaveInvalidPelicula() {
        Pelicula pelicula = new Pelicula();
        Exception exception = assertThrows(ConstraintViolationException.class, () -> {
            peliculaRepository.saveAndFlush(pelicula);
        });

        String expectedMessage = "No puede ingresar un director vacio";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}

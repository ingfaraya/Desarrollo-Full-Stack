package com.example.pelicula.repository;

import com.example.pelicula.model.Pelicula;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

/*
Explicación de las pruebas
testSavePelicula: Verifica que una película se guarde correctamente en la base de datos.
testFindById: Verifica que se pueda encontrar una película por su ID.
testDeletePelicula: Verifica que una película pueda ser eliminada.
testFindAllPeliculas: Verifica que se puedan recuperar todas las películas guardadas.
testUpdatePelicula: Verifica que una película pueda ser actualizada correctamente.
testSavePeliculaWithConstraintViolation: Verifica que la base de datos arroje una excepción si se intenta guardar una película sin los campos obligatorios.
 */

@DataJpaTest
public class PeliculaRepositoryTest {

    @Autowired
    private PeliculaRepository peliculaRepository;

    @Test
    public void testSavePelicula() {
        Pelicula pelicula = new Pelicula();
        pelicula.setTitulo("Inception");
        pelicula.setAno("2010");
        pelicula.setDirector("Christopher Nolan");
        pelicula.setGenero("Ciencia Ficción");
        pelicula.setSinopsis("Un ladrón que roba secretos corporativos...");
        Pelicula savedPelicula = peliculaRepository.save(pelicula);

        assertNotNull(savedPelicula.getId());
        assertEquals("Inception", savedPelicula.getTitulo());
    }

    @Test
    public void testFindById() {
        Pelicula pelicula = new Pelicula();
        pelicula.setTitulo("Inception");
        pelicula.setAno("2010");
        pelicula.setDirector("Christopher Nolan");
        pelicula.setGenero("Ciencia Ficción");
        pelicula.setSinopsis("Un ladrón que roba secretos corporativos...");
        peliculaRepository.save(pelicula);

        Optional<Pelicula> foundPelicula = peliculaRepository.findById(pelicula.getId());
        assertTrue(foundPelicula.isPresent());
        assertEquals("Inception", foundPelicula.get().getTitulo());
    }

    @Test
    public void testDeletePelicula() {
        Pelicula pelicula = new Pelicula();
        pelicula.setTitulo("Inception");
        pelicula.setAno("2010");
        pelicula.setDirector("Christopher Nolan");
        pelicula.setGenero("Ciencia Ficción");
        pelicula.setSinopsis("Un ladrón que roba secretos corporativos...");
        pelicula = peliculaRepository.save(pelicula);

        peliculaRepository.delete(pelicula);
        Optional<Pelicula> deletedPelicula = peliculaRepository.findById(pelicula.getId());
        assertFalse(deletedPelicula.isPresent());
    }

    @Test
    public void testFindAllPeliculas() {
        Pelicula pelicula1 = new Pelicula();
        pelicula1.setTitulo("Inception");
        pelicula1.setAno("2010");
        pelicula1.setDirector("Christopher Nolan");
        pelicula1.setGenero("Ciencia Ficción");
        pelicula1.setSinopsis("Un ladrón que roba secretos corporativos...");
        peliculaRepository.save(pelicula1);

        Pelicula pelicula2 = new Pelicula();
        pelicula2.setTitulo("Interstellar");
        pelicula2.setAno("2014");
        pelicula2.setDirector("Christopher Nolan");
        pelicula2.setGenero("Aventura Espacial");
        pelicula2.setSinopsis("Un grupo de exploradores...");
        peliculaRepository.save(pelicula2);

        List<Pelicula> peliculas = peliculaRepository.findAll();
        assertEquals(2, peliculas.size());
    }

    @Test
    public void testUpdatePelicula() {
        Pelicula pelicula = new Pelicula();
        pelicula.setTitulo("Inception");
        pelicula.setAno("2010");
        pelicula.setDirector("Christopher Nolan");
        pelicula.setGenero("Ciencia Ficción");
        pelicula.setSinopsis("Un ladrón que roba secretos corporativos...");
        pelicula = peliculaRepository.save(pelicula);

        pelicula.setTitulo("Inception Updated");
        Pelicula updatedPelicula = peliculaRepository.save(pelicula);
        assertEquals("Inception Updated", updatedPelicula.getTitulo());
    }

    @Test
    public void testSavePeliculaWithConstraintViolation() {
        Pelicula pelicula = new Pelicula();
        assertThrows(DataIntegrityViolationException.class, () -> {
            peliculaRepository.save(pelicula);  // intentando guardar sin campos obligatorios
        });
    }
}

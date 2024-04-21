package com.example.pelicula.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.pelicula.model.Pelicula;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PeliculaRepositoryTest {
    @Autowired
    private PeliculaRepository studentRepository;

    @Test
    public void guardarPeliculaTest() {
        Pelicula estudiante = new Pelicula();
        estudiante.setName("John Doe");

        Pelicula resultado = studentRepository.save(estudiante);

        assertNotNull(resultado.getId());
        assertEquals("John Doe", resultado.getName());
    }

}

package com.example.pelicula.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.pelicula.model.Pelicula;
import com.example.pelicula.repository.PeliculaRepository;

@ExtendWith(MockitoExtension.class)
public class PeliculaServiceTest {
    @InjectMocks
    private PeliculaServiceImpl estudianteServicio;

    @Mock
    private PeliculaRepository estudianteRepositoryMock;

    @Test
    public void guardarPeliculaTest() {

        Pelicula estudiante = new Pelicula();
        estudiante.setName("Jose Rondon");

        when(estudianteRepositoryMock.save(any())).thenReturn(estudiante);

        Pelicula resultado = estudianteServicio.createPelicula(estudiante);

        assertEquals("Jose Rondon", resultado.getName());
    }
}

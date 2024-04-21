package com.example.pelicula.service;

import com.example.pelicula.model.Pelicula;
import java.util.List;
import java.util.Optional;

public interface PeliculaService {
    List<Pelicula> getAllPeliculas();
    Optional<Pelicula> getPeliculaById(Long id);
    Pelicula createPelicula(Pelicula student);
    Pelicula updatePelicula(Long id,Pelicula student);
    void deletePelicula(Long id);
}

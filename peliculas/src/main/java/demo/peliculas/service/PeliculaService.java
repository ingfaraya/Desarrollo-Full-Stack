package demo.peliculas.service;

import demo.peliculas.model.Pelicula;
import java.util.List;
import java.util.Optional;

public interface PeliculaService {

    List<Pelicula> getAllPeliculas();
    Optional<Pelicula> getPeliculaById(Long id);

}

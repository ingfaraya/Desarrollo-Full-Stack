package demo.peliculas.service;

import demo.peliculas.model.Pelicula;
import java.util.List;
import java.util.Optional;

public interface PeliculaService {

    List<Pelicula> getAllPeliculas();
    Optional<Pelicula> getPeliculaById(Long id);
    Pelicula createdPelicula(Pelicula pelicula);
    Pelicula updatePelicula(Long id, Pelicula pelicula);
    void deletePelicula(Long id);

}

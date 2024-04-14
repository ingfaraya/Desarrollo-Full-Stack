package demo.peliculas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.peliculas.model.Pelicula;

public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {

}

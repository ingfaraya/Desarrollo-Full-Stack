package com.example.pelicula.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pelicula.model.Pelicula;

public interface PeliculaRepository extends JpaRepository<Pelicula, Long>{
    
}

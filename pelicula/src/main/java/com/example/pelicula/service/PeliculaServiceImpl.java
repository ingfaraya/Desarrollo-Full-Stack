package com.example.pelicula.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pelicula.model.Pelicula;
import com.example.pelicula.repository.PeliculaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PeliculaServiceImpl implements PeliculaService{
    @Autowired
    private PeliculaRepository peliculaRepository;

    @Override
    public List<Pelicula> getAllPeliculas() {
        return peliculaRepository.findAll();
    }

    @Override
    public Optional<Pelicula> getPeliculaById(Long id) {
        return peliculaRepository.findById(id);
    }
    
    @Override
    public Pelicula createPelicula(Pelicula pelicula){
        return peliculaRepository.save(pelicula);
    }

    @Override
    public Pelicula updatePelicula(Long id, Pelicula pelicula) {
        return peliculaRepository.findById(id)
            .map(existingPelicula -> {
                pelicula.setId(id);
                return peliculaRepository.save(pelicula);
            })
            .orElseThrow(() -> new RuntimeException("Pelicula not found with id: " + id));  // Consider using a custom exception
    }

    @Override
    public void deletePelicula(Long id){
        peliculaRepository.deleteById(id);
    }
}

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
    private PeliculaRepository studentRepository;

    @Override
    public List<Pelicula> getAllPeliculas() {
        return studentRepository.findAll();
    }

    @Override
    public Optional<Pelicula> getPeliculaById(Long id) {
        return studentRepository.findById(id);
    }
    
    @Override
    public Pelicula createPelicula(Pelicula student){
        return studentRepository.save(student);
    }

    @Override
    public Pelicula updatePelicula(Long id, Pelicula student){
        if(studentRepository.existsById(id)){
            student.setId(id);
            return studentRepository.save(student);
        }   else {
                return null;
        }
    }

    @Override
    public void deletePelicula(Long id){
        studentRepository.deleteById(id);
    }
}

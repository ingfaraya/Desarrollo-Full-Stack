package demo.peliculas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import demo.peliculas.model.Pelicula;
import demo.peliculas.repository.PeliculaRepository;

@Service
public class PeliculaServiceImpl implements PeliculaService {

    @Autowired
    private PeliculaRepository peliculaRepository;

    @Override
    public List<Pelicula> getAllPeliculas() {
        try {
            return peliculaRepository.findAll();
        } catch (Exception e) {
            System.out.println("getAllPeliculas: " + e.toString());
            return null;
        }
    }

    @Override
    public Optional<Pelicula> getPeliculaById(Long id) {
        try {
            if (peliculaRepository.existsById(id)) {
                return peliculaRepository.findById(id);
                
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            System.out.println("getPeliculaById [id][" + id + "]: " + e.toString());
            return Optional.empty();
        }
    }

    @Override
    public Pelicula createdPelicula(Pelicula pelicula) {
        try {
            pelicula.setTitulo(pelicula.getTitulo());
            pelicula.setAño(pelicula.getAño());
            pelicula.setDirector(pelicula.getDirector());
            pelicula.setGenero(pelicula.getGenero());
            pelicula.setSinopsis(pelicula.getSinopsis());
            return peliculaRepository.save(pelicula);
        } catch (Exception e) {
            System.out.println("createdPelicula [pelicula][" + pelicula + "]: " + e.toString());
            return pelicula;
        }
    }

    @Override
    public Pelicula updatePelicula(Long id, Pelicula pelicula) {
        try {
            if (peliculaRepository.existsById(id)) {
                pelicula.setId(id);
                return peliculaRepository.save(pelicula);
                
            } else {
                return pelicula;
            }
        } catch (Exception e) {
            System.out.println("updatePelicula [id][" + id + "]: " + e.toString());
            return pelicula;
        }
    }

    @Override
    public void deletePelicula(Long id) {
        try {
            peliculaRepository.deleteById(id);
            
        } catch (Exception e) {
            System.out.println("deletePelicula ['id'][" + id + "]: " + e.toString());
        }
    }
}

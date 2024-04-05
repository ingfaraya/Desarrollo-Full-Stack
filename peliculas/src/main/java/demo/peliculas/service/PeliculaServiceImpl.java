package demo.peliculas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.peliculas.model.Pelicula;
import demo.peliculas.repository.PeliculaRepository;

@Service
public class PeliculaServiceImpl implements PeliculaService {

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
    public Pelicula createdPelicula(Pelicula pelicula) {
        return peliculaRepository.save(pelicula);
    }

    @Override
    public Pelicula updatePelicula(Long id, Pelicula pelicula) {
        try {
            if (peliculaRepository.existsById(id)) {
                pelicula.setId(id);
                return peliculaRepository.save(pelicula);
                
            } else {
                return null;
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return pelicula;
    }

    @Override
    public void deletePelicula(Long id) {
        try {
            peliculaRepository.deleteById(id);
            
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

}

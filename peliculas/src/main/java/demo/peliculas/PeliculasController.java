package demo.peliculas;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PeliculasController {
    private List<Peliculas> peliculas = new ArrayList<>();

    //id, titulo, año, director, género y sinopsis
    public PeliculasController () {
        peliculas.add(new Peliculas(1, "Asesino 1", "2000", "Juanito", "Terror", "Un estudiante escapa de un asesino"));
        peliculas.add(new Peliculas(2, "Asesino 2", "2001", "Juanito", "Terror", "Un profesor escapa de un asesino"));
        peliculas.add(new Peliculas(3, "Asesino 3", "2002", "Juanito", "Terror", "Un policia escapa de un asesino"));
        peliculas.add(new Peliculas(4, "Asesino 4", "2003", "Juanito", "Terror", "Un informatico escapa de un asesino"));
        peliculas.add(new Peliculas(5, "Asesino 5", "2004", "Juanito", "Terror", "Un doctor escapa de un asesino"));

    }

    @GetMapping("/peliculas")
    public List<Peliculas> getPeliculas() {
        return peliculas;
    }

    @GetMapping("/peliculas/{id}")
    public Peliculas getPeliculasById(@PathVariable int id) {
        for (Peliculas pelicula : peliculas){
            if(pelicula.getId() == id){
                return pelicula;
            }
        }
        return null;
    }
}

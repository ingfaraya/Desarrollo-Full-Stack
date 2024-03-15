package demo.peliculas;

public class Peliculas {
    private int id;
    private String titulo;
    private String año;
    private String director;
    private String genero;
    private String sinopsis;

    //id, titulo, año, director, género y sinopsis
    public Peliculas(int id, String titulo, String año, String director, String genero, String sinopsis){
        this.id = id;
        this.titulo = titulo;
        this.año = año;
        this.director = director;
        this.genero = genero;
        this.sinopsis = sinopsis;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAño() {
        return año;
    }

    public String getDirector() {
        return director;
    }

    public String getGenero() {
        return genero;
    }

    public String getSinopsis() {
        return sinopsis;
    }
}

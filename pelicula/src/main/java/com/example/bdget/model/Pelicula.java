package com.example.pelicula.model;
import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "peliculas")
public class Pelicula extends RepresentationModel<Pelicula>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "No puede ingresar un titulo vacio")
    @Column(name= "titulo")
    private String titulo;

    @NotBlank(message = "No puede ingresar un año vacio")
    @Column(name= "año")
    private String ano;

    @NotBlank(message = "No puede ingresar un director vacio")
    @Column(name= "director")
    private String director;

    @NotBlank(message = "No puede ingresar un genero vacio")
    @Column(name= "genero")
    private String genero;

    @NotBlank(message = "No puede ingresar un genero vacio")
    @Column(name= "sinopsis")
    private String sinopsis;

    public Pelicula(Long id, String titulo, String ano, String director, String genero, String sinopsis) {
        this.id = id;
        this.titulo = titulo;
        this.ano = ano;
        this.director = director;
        this.genero = genero;
        this.sinopsis = sinopsis;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }


}
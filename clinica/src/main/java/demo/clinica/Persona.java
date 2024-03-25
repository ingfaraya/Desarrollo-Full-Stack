package demo.clinica;

import java.util.List;
import java.util.Map;

public class Persona {

    private int id;
    private String name;
    private int age;
    private String direccion;
    private List<Rol> roles;
    private List<Clinica> clinicas;
    private List<Historico> historicos;
    private Map<String, List<String>> nombresPorRol;

    

    public Persona(int id, String name, int age, String direccion, List<Rol> roles, List<Clinica> clinicas,
            List<Historico> historicos, Map<String, List<String>> nombresPorRol) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.direccion = direccion;
        this.roles = roles;
        this.clinicas = clinicas;
        this.historicos = historicos;
        this.nombresPorRol = nombresPorRol;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getDireccion() {
        return direccion;
    }

    public List<Rol> getRoles() {
        return roles;
    }

    public List<Clinica> getClinicas() {
        return clinicas;
    }

    public List<Historico> getHistoricos() {
        return historicos;
    }

    public Map<String, List<String>> getNombresPorRol() {
        return nombresPorRol;
    }

    
}

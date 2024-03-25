package demo.tienda;

import java.util.List;
import java.util.Map;

public class Usuario {

    private int id;
    private String name;
    private int age;
    private String direccion;
    private List<Rol> roles;
    private List<Sucursal> sucursales;
    private Map<String, List<String>> nombresPorRol;

    public Usuario(int id, String name, int age, String direccion, List<Sucursal> sucursales, List<Rol> roles, Map<String, List<String>> nombresPorRol) {

        this.id = id;
        this.name = name;
        this.age = age;
        this.direccion = direccion;
        this.roles = roles;
        this.sucursales = sucursales;
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

    public List<Rol> getRoles(){
        return roles;
    }

    public List<Sucursal> getSucursales(){
        return sucursales;
    }

    public Map<String, List<String>> getNombresPorRol(){
        return nombresPorRol;
    }
}

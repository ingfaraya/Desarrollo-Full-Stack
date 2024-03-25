package demo.tienda;

public class Rol {

    private String codigo;
    private String nombre;

    public Rol(String codigo, String nombre){
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }
}

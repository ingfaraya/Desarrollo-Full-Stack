package demo.tienda;

public class Sucursal {

    private String codigo;
    private String nombre;
    private String direccion;

    public Sucursal(String codigo, String nombre, String direccion){
        this.codigo = codigo;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }
}

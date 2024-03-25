package demo.clinica;

public class Historico {

    private String codigo;
    private String nombre;
    private String id_persona;
    private String ruta;
    private String detalle;
    private String fecha;

    public Historico(String codigo, String nombre, String id_persona, String ruta, String detalle, String fecha) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.id_persona = id_persona;
        this.ruta = ruta;
        this.detalle = detalle;
        this.fecha = fecha;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getId_persona() {
        return id_persona;
    }

    public String getRuta() {
        return ruta;
    }

    public String getDetalle() {
        return detalle;
    }

    public String getFecha() {
        return fecha;
    }
}

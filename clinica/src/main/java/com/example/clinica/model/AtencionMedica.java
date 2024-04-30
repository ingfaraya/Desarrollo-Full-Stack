package com.example.clinica.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Atenciones_Medicas")
public class AtencionMedica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_Historial_Medico", referencedColumnName = "id")
    private HistorialMedico historialMedico;

    @Column(name = "Fecha_Atencion")
    @Temporal(TemporalType.DATE)
    private Date fechaAtencion;

    @Column(name = "Descripcion")
    private String descripcion;

    @Column(name = "Costo")
    private double costo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HistorialMedico getHistorialMedico() {
        return historialMedico;
    }

    public void setHistorialMedico(HistorialMedico historialMedico) {
        this.historialMedico = historialMedico;
    }

    public Date getFechaAtencion() {
        return fechaAtencion;
    }

    public void setFechaAtencion(Date fechaAtencion) {
        this.fechaAtencion = fechaAtencion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }
}

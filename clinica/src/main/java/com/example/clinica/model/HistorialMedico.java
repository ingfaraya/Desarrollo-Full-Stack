package com.example.clinica.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Historial_Medico")
public class HistorialMedico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_Paciente", referencedColumnName = "id")
    private Paciente paciente;

    @Column(name = "Fecha_Consulta")
    @Temporal(TemporalType.DATE)
    private Date fechaConsulta;

    @Column(name = "Motivo_Consulta")
    private String motivoConsulta;

    @Column(name = "Diagnostico")
    private String diagnostico;

    @Column(name = "Tratamiento")
    private String tratamiento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Date getFechaConsulta() {
        return fechaConsulta;
    }

    public void setFechaConsulta(Date fechaConsulta) {
        this.fechaConsulta = fechaConsulta;
    }

    public String getMotivoConsulta() {
        return motivoConsulta;
    }

    public void setMotivoConsulta(String motivoConsulta) {
        this.motivoConsulta = motivoConsulta;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }
}

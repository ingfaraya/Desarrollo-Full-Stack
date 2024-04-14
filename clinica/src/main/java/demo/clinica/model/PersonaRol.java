package demo.clinica.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "personas_roles")
@IdClass(PersonaRolId.class)
public class PersonaRol {

    @Id
    @Column(name = "persona_id")
    private Integer personaId;

    @Id
    @Column(name = "rol_id")
    private Integer rolId;

    @ManyToOne
    @JoinColumn(name = "persona_id", insertable = false, updatable = false)
    private Persona persona;

    @ManyToOne
    @JoinColumn(name = "rol_id", insertable = false, updatable = false)
    private Rol rol;

    public Integer getPersonaId() {
        return personaId;
    }

    public void setPersonaId(Integer personaId) {
        this.personaId = personaId;
    }

    public Integer getRolId() {
        return rolId;
    }

    public void setRolId(Integer rolId) {
        this.rolId = rolId;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    // Constructors, getters, and setters
}

class PersonaRolId implements Serializable {
    private Integer personaId;
    private Integer rolId;

    // Constructors, equals, and hashCode methods
}

package demo.clinica.service;

import demo.clinica.model.Persona;
import java.util.List;
import java.util.Optional;

public interface PersonaService {
    List<Persona> findAll();
    Optional<Persona> findById(int id);
    Optional<String> findDireccionesByRol(int idPersona, String nombreRol);
}

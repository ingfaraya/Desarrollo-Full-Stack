package demo.clinica.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import demo.clinica.model.Persona;
import demo.clinica.repository.PersonaRepository;

@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    @Override
    public List<Persona> findAll() {
        return personaRepository.findAll();
    }

    @Override
    public Optional<Persona> findById(int id) {
        return personaRepository.findById(id);
    }

    @Override
    public Optional<String> findDireccionesByRol(int idPersona, String nombreRol) {
        return personaRepository.findDireccionesByRol(idPersona, nombreRol);
    }
}

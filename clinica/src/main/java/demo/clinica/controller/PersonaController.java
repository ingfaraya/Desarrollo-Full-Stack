package demo.clinica.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

import demo.clinica.model.Persona;
import demo.clinica.service.PersonaService;

@RestController
@RequestMapping("/personas")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @GetMapping
    public List<Persona> getAllPersonas() {
        return personaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Persona> getPersonaById(@PathVariable int id) {
        Optional<Persona> persona = personaService.findById(id);
        return persona.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{idPersona}/rol/{nombreRol}")
    public ResponseEntity<String> getDireccionesPorRol(@PathVariable("idPersona") Integer idPersona, @PathVariable("nombreRol") String nombreRol) {
        Optional<String> direcciones = personaService.findDireccionesByRol(idPersona, nombreRol);
        return direcciones.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}

package demo.clinica;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

@RestController
public class PersonaController {
    private List<Persona> personas = new ArrayList<>();

    public PersonaController() {

        // Inicializar la lista con datos
        personas.add(new Persona(1, "Sebastian", 23, "Avenida test 12345",
        Arrays.asList(new Rol("pas2231", "paciente")),
        Arrays.asList(new Clinica("ave333", "puente", "puente 123")),
        Arrays.asList(
            new Historico("1-44lp", "dolores-34", "1", "/historicos/doc/1/dolores", "Paciente llega con mucho dolor en la madrugada, se le receta paracetamol", "24-03-2024")
            ),
        llenarListaRoles("paciente", "Sebastian")));

        personas.add(new Persona(2, "Daniel", 25, "Avenida prueba 123",
        Arrays.asList(new Rol("pas2231", "paciente")),
        Arrays.asList(new Clinica("ave333", "puente", "puente 123")),
        Arrays.asList(
            new Historico("2-44lp", "dolores-34", "2", "/historicos/doc/2/dolores", "Paciente llega con mucho dolor en la tarde, se le receta paracetamol", "23-03-2024")
            ),
        llenarListaRoles("paciente", "Daniel")));

        personas.add(new Persona(3, "Pedro", 30, "Avenida grande 123",
        Arrays.asList(new Rol("doc4435", "doctor")),
        Arrays.asList(new Clinica("dav333", "Davila", "puente 123")),
        Arrays.asList(
            new Historico("3-44lp", "atenciones-34", "3", "/historicos/doc/3/atenciones", "Hoy me toco atender a 10 personas en un rango de 10 horas", "22-03-2024"),
            new Historico("3-45lp", "atenciones-35", "3", "/historicos/doc/3/atenciones", "Hoy me toco atender a 29 personas en un rango de 10 horas", "23-03-2024")
            ),
        llenarListaRoles("doctor", "Pedro")));  

    }

    private Map<String, List<String>> llenarListaRoles(String rol, String nombreusuario){
        Map<String, List<String>> retorno = new HashMap<String, List<String>>();
        List<String> personas = Arrays.asList(nombreusuario);
        retorno.put(rol, personas);
        return retorno;
    }

    @GetMapping("/personas")
    public List<Persona> getPersonas() {
        return personas;
    }

    @GetMapping("/personas/{id}")
    public Persona getPersonaById(@PathVariable int id) {
        for (Persona usuario : personas) {
            if (usuario.getId() == id) {
                return usuario;
            }
        }
        return new Persona(0, null, 0, null, null, null, null, null);
    }

    @GetMapping(path = "/personas/{idPersona}/rol/{nombreRol}")
    public String listarRol(@PathVariable("idPersona") Integer idPersona, @PathVariable("nombreRol") String nombreRol) {
        String grupoDir = "";
        for (Persona usuario : personas) {
            if (usuario.getId() == idPersona) {
                List<String> direcciones = usuario.getNombresPorRol().get(nombreRol);
                for (String direccion : direcciones) {
                    grupoDir = grupoDir + direccion;
                }
            }
        }
        return grupoDir;
    }
}
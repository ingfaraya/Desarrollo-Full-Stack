package demo.tienda;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

@RestController
public class UsuarioController {
    private List<Usuario> usuarios = new ArrayList<>();

    public UsuarioController() {

        // Inicializar la lista con datos
        usuarios.add(new Usuario(1, "Sebastian", 23, "Avenida test 12345",
        Arrays.asList(new Sucursal("ave333", "puente", "puente 123")),
        Arrays.asList(new Rol("uni888", "vendedor")),
        llenarListaRoles("vendedor", "Sebastian")));

        usuarios.add(new Usuario(2, "Daniel", 25, "Avenida prueba 123",
        Arrays.asList(new Sucursal("ave333", "puente", "puente 123")),
        Arrays.asList(new Rol("cli334", "cliente")),
        llenarListaRoles("cliente", "Daniel")));

usuarios.add(new Usuario(3, "Pedro", 30, "Avenida grande 123",
        Arrays.asList(new Sucursal("ave333", "puente", "puente 123")),
        Arrays.asList(new Rol("cli334", "cliente")),
        llenarListaRoles("cliente", "Pedro")));  

    }

    private Map<String, List<String>> llenarListaRoles(String rol, String nombreusuario){
        Map<String, List<String>> retorno = new HashMap<String, List<String>>();
        List<String> usuarios = Arrays.asList(nombreusuario);
        retorno.put(rol, usuarios);
        return retorno;
    }

    @GetMapping("/usuarios")
    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    @GetMapping("/usuarios/{id}")
    public Usuario getUsuarioById(@PathVariable int id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId() == id) {
                return usuario;
            }
        }
        return new Usuario(0, null, 0, null, null, null, null);
    }

    @GetMapping(path = "/usuarios/{idUsuario}/rol/{nombreRol}")
    public String listarRol(@PathVariable("idUsuario") Integer idUsuario, @PathVariable("nombreRol") String nombreRol) {
        String grupoDir = "";
        for (Usuario usuario : usuarios) {
            if (usuario.getId() == idUsuario) {
                List<String> direcciones = usuario.getNombresPorRol().get(nombreRol);
                for (String direccion : direcciones) {
                    grupoDir = grupoDir + direccion;
                }
            }
        }
        return grupoDir;
    }
}
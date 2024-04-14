package demo.login.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import demo.login.model.Login;
import demo.login.service.LoginService;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/login")
public class LoginController {


    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginService loginService;

    @GetMapping
    public List<Login> getAllLogin() {
        log.info("GET /login");
        log.info("Retornando todos los login");
        return loginService.getAllLogin();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getLoginById(@PathVariable Long id) {
        Optional<Login> login = loginService.getLoginById(id);
        if (login.isEmpty()) {
            log.error("No se encontró el login con ID {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("No se encontró el login con ID " + id));
        }
        return ResponseEntity.ok(login);
    }

    @GetMapping("/username/{username}/password/{password}")
    public ResponseEntity<Object> getLoginByUsernameAndPassword(@PathVariable String username, @PathVariable String password){
        Optional<Login> login = loginService.getLoginByUsernameAndPassword(username, password);
        if (login.isEmpty()) {
            log.error("No se encontró el login con username {}", username);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("No se encontró el login con username " + username));
        }
        return ResponseEntity.ok(login);
    }

    @PostMapping
    public ResponseEntity<Object> createLogin(@RequestBody Login login) {
        Optional<Login> _login = Optional.ofNullable(loginService.createdLogin(login));
        if (_login.isEmpty()) {
            log.error("No se creo el login con username {}", _login);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("No se encontró el login con username " + _login));
        }
        return ResponseEntity.ok(_login);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateLogin(@PathVariable Long id, @RequestBody Login login) {     
        Optional<Login> _login = Optional.ofNullable(loginService.updateLogin(id, login));
        if (_login.isEmpty()) {
            log.error("No se actualizo el login con id {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("No se actualizo el login con id " + id));
        }
        return ResponseEntity.ok(_login);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteLogin (@PathVariable Long id){
        Optional<Login> login = loginService.getLoginById(id);
        if (login.isEmpty()) {
            log.error("No se encontró el login con ID {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("No se encontró el login con ID " + id));
        }
        log.error("Se elimino el id {}", id);
        loginService.deleteLogin(id);
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("Se elimino el id " + id));
    }

    static class MessageResponse {
        private final String message;
    
        public MessageResponse(String message) {
            this.message = message;
        }
    
        public String getMessage() {
            return message;
        }
    }
    
}

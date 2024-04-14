package demo.clinica.service;

import demo.clinica.model.Login;
import java.util.List;
import java.util.Optional;

public interface LoginService {
    Optional<Login> findByUsername(String username);
    Login save(Login login);
}

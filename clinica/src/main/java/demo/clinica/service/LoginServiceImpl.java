package demo.clinica.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.clinica.model.Login;
import demo.clinica.repository.LoginRepository;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginRepository loginRepository;

    public Login save(Login login) {
        return loginRepository.save(login);
    }

    public Optional<Login> findByUsername(String username) {
        return Optional.ofNullable(loginRepository.findByUsername(username).orElse(null));
    }
}

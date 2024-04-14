package demo.login.service;

import demo.login.model.Login;
import demo.login.repository.LoginRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginRepository loginRepository;

    @Override
    public List<Login> getAllLogin() {
        try {
            return loginRepository.findAll();
        } catch (Exception e) {
            System.out.println("getAllLogins: " + e.toString());
            return null;
        }
    }

    @Override
    public Optional<Login> getLoginById(Long id) {
        try {
            if (loginRepository.existsById(id)) {
                return loginRepository.findById(id);
                
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            System.out.println("getLoginById [id][" + id + "]: " + e.toString());
            return Optional.empty();
        }
    }

    @Override
    public Optional<Login> getLoginByUsernameAndPassword(String username, String passwor) {
        return loginRepository.checkCredentials(username, passwor);
    }

    @Override
    public Login createdLogin(Login login) {
        try {
            login.setUsername(login.getUsername());
            login.setPassword(login.getPassword());
            return loginRepository.save(login);
        } catch (Exception e) {
            System.out.println("createdLogin [login][" + login + "]: " + e.toString());
            return login;
        }
    }

    @Override
    public Login updateLogin(Long id, Login login) {
        try {
            if (loginRepository.existsById(id)) {
                login.setId(id);
                return loginRepository.save(login);
                
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("updateLogin [id][" + id + "]: " + e.toString());
            return login;
        }
    }

    @Override
    public void deleteLogin(Long id) {
        try {
            loginRepository.deleteById(id);
            
        } catch (Exception e) {
            System.out.println("deleteLogin ['id'][" + id + "]: " + e.toString());
        }
    }
}

package demo.login.service;

import demo.login.model.Login;
import java.util.List;
import java.util.Optional;

public interface LoginService {

    List<Login> getAllLogin();
    Optional<Login> getLoginById(Long id);
    Optional<Login> getLoginByUsernameAndPassword(String username, String passwor);
    Login createdLogin(Login login);
    Login updateLogin(Long id, Login login);
    void deleteLogin(Long id);

}

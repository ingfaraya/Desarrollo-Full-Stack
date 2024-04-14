package demo.login.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import demo.login.model.Login;

public interface LoginRepository extends JpaRepository<Login, Long> {

    // Consulta para encontrar un Login por nombre de usuario
    Optional<Login> findByUsername(String username);

    // Consulta personalizada para verificar las credenciales de un usuario
    @Query("SELECT l FROM Login l WHERE l.username = :username AND l.password = :password")
    Optional<Login> checkCredentials(String username, String password);
}

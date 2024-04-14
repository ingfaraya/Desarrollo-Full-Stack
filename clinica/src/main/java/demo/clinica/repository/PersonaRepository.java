package demo.clinica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import demo.clinica.model.Persona;
import java.util.Optional;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Integer> {

    // Método para encontrar direcciones basadas en el rol de una persona
    @Query("SELECT STRING_AGG(d.direccion, ', ') " +
           "FROM Persona p " +
           "JOIN p.roles r " +
           "JOIN r.direcciones d " +  // Asumiendo que hay una relación de muchos a muchos con una entidad Direcciones
           "WHERE p.id = :idPersona AND r.nombre = :nombreRol " +
           "GROUP BY p.id")
    Optional<String> findDireccionesByRol(int idPersona, String nombreRol);
}

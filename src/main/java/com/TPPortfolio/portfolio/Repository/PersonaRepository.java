
package com.TPPortfolio.portfolio.Repository;


import com.TPPortfolio.portfolio.Entity.Persona;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Integer> {
    Optional<Persona> findByNombre(String nombre);
    boolean existsByNombre(String nombre);
}
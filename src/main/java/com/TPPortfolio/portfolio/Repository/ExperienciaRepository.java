
package com.TPPortfolio.portfolio.Repository;

import com.TPPortfolio.portfolio.Entity.Experiencia;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperienciaRepository extends JpaRepository<Experiencia, Integer> {
    Optional<Experiencia> findByNombre(String nombrePro);
     boolean existsByNombre(String nombrePro);
    
}

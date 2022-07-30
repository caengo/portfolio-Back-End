
package com.TPPortfolio.portfolio.Repository;

import com.TPPortfolio.portfolio.Entity.Acercade;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcercadeRepository extends JpaRepository<Acercade, Integer> {
   
}


package com.TPPortfolio.portfolio.Service;

import com.TPPortfolio.portfolio.Entity.Acercade;
import com.TPPortfolio.portfolio.Repository.AcercadeRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AcercadeService {
    
    @Autowired
    AcercadeRepository acercadeRepository;
    
    public Optional<Acercade> getOne(int id){
        return acercadeRepository.findById(id);
    }

    public void  save(Acercade acercade){
        acercadeRepository.save(acercade);
    }

    public void delete(int id){
        acercadeRepository.deleteById(id);
    }

    public boolean existsById(int id){
        return acercadeRepository.existsById(id);
    }
    
    
}

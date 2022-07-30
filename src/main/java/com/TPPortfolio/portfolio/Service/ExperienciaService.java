
package com.TPPortfolio.portfolio.Service;

import com.TPPortfolio.portfolio.Entity.Experiencia;
import com.TPPortfolio.portfolio.Repository.ExperienciaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ExperienciaService {
    
    @Autowired
    ExperienciaRepository experienciaRepository;
    
    public List<Experiencia> list(){
        return experienciaRepository.findAll();
}
    public Optional<Experiencia> getOne(int id){
        return experienciaRepository.findById(id);
    }
    public Optional<Experiencia> getByNombre(String nombrePro){
        return experienciaRepository.findByNombre(nombrePro);
    }

    public void  save(Experiencia experiencia){
        experienciaRepository.save(experiencia);
    }

    public void delete(int id){
        experienciaRepository.deleteById(id);
    }

    public boolean existsById(int id){
        return experienciaRepository.existsById(id);
    }

    public boolean existsByNombre(String nombrePro){
        return experienciaRepository.existsByNombre(nombrePro);
    }
}

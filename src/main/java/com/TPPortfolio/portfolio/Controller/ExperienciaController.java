
package com.TPPortfolio.portfolio.Controller;

import com.TPPortfolio.portfolio.Dto.ExperienciaDto;
import com.TPPortfolio.portfolio.Dto.Mensaje;
import com.TPPortfolio.portfolio.Entity.Experiencia;
import com.TPPortfolio.portfolio.Entity.Persona;
import com.TPPortfolio.portfolio.Security.Entity.UsuarioPrincipal;
import com.TPPortfolio.portfolio.Service.ExperienciaService;
import com.TPPortfolio.portfolio.Service.PersonaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/experiencias")
@CrossOrigin
public class ExperienciaController {
    
    @Autowired
    ExperienciaService experienciaService;
    @Autowired
    PersonaService personaService;
   
    @GetMapping("{id}")
    public ResponseEntity<Experiencia> getById(@PathVariable("id") int id) {
        if (!experienciaService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Experiencia experiencia = experienciaService.getOne(id).get();
        experiencia.setIdPersona(experiencia.getPersona().getId());
        return new ResponseEntity<Experiencia>(experiencia, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody ExperienciaDto experienciaDto) {
        if (!personaService.existsById(experienciaDto.getIdPersona()))
            return new ResponseEntity(new Mensaje("la persona no existe"), HttpStatus.BAD_REQUEST);
        if (GetIdUsuarioPersona(experienciaDto.getIdPersona()) != GetIdUsuarioLogueado())
            return new ResponseEntity(new Mensaje("el usuario logueado no tiene acceso a la persona"), HttpStatus.BAD_REQUEST);  
        Persona persona = new Persona();
        persona.setId(experienciaDto.getIdPersona());
        Experiencia experiencia = new Experiencia (experienciaDto.getFechaInicio(), experienciaDto.getFechaFinal(),
                experienciaDto.getNombre(), experienciaDto.getResenia(), experienciaDto.getCargo(),
                experienciaDto.getDescripcion(), experienciaDto.getReferencia(),
                experienciaDto.getIdPersona());
        experiencia.setPersona(persona);
        experienciaService.save(experiencia);
        return new ResponseEntity(new Mensaje("experiencia creada"), HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('USER')")
    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody ExperienciaDto experienciaDto) {
        if (!experienciaService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        if (!personaService.existsById(experienciaDto.getIdPersona()))
            return new ResponseEntity(new Mensaje("la persona no existe"), HttpStatus.BAD_REQUEST);
        
        int idUsuarioLogueado = GetIdUsuarioLogueado();
        int idPersonaOriginal = GetIdPersonaExperiencia(id);
        if (GetIdUsuarioPersona(idPersonaOriginal) != idUsuarioLogueado)
            return new ResponseEntity(new Mensaje("el usuario logueado no tiene acceso a la experiencia"), HttpStatus.BAD_REQUEST);
        if (experienciaDto.getIdPersona() != idPersonaOriginal)
            return new ResponseEntity(new Mensaje("no es posible asignar la experiencia a otra persona"), HttpStatus.BAD_REQUEST);
        
        Persona persona = new Persona();
        persona.setId(experienciaDto.getIdPersona());
        
        Experiencia experiencia = experienciaService.getOne(id).get();
        experiencia.setFechaInicio(experienciaDto.getFechaInicio());
        experiencia.setFechaFinal(experienciaDto.getFechaFinal());
        experiencia.setNombre(experienciaDto.getNombre());
        experiencia.setResenia(experienciaDto.getResenia());
        experiencia.setCargo(experienciaDto.getCargo());
        experiencia.setDescripcion(experienciaDto.getDescripcion());
        experiencia.setReferencia(experienciaDto.getReferencia());
        experiencia.setPersona(persona);
        
        experienciaService.save(experiencia);
        return new ResponseEntity(new Mensaje("experiencia actualizada"), HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if (!experienciaService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        int idPersonaOriginal = GetIdPersonaExperiencia(id);
        if (GetIdUsuarioPersona(idPersonaOriginal) != GetIdUsuarioLogueado())
            return new ResponseEntity(new Mensaje("el usuario logueado no tiene acceso a la experiencia"), HttpStatus.BAD_REQUEST);    
        experienciaService.delete(id);
        return new ResponseEntity(new Mensaje("experiencia eliminada"), HttpStatus.OK);

    }
    
    private int GetIdPersonaExperiencia(int idExperiencia){
        Experiencia experiencia = experienciaService.getOne(idExperiencia).get();
        return experiencia.getPersona().getId();
    }
    
    private int GetIdUsuarioPersona(int idPersona){
        Persona persona = personaService.getOne(idPersona).get();
        return persona.getUsuario().getId();
    }
    
    private int GetIdUsuarioLogueado(){
        var usuarioObjeto = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UsuarioPrincipal usuario = (UsuarioPrincipal)usuarioObjeto;
        return usuario.getId();
    }
    
        /*
     @GetMapping("{nombre}")
    public ResponseEntity<Experiencia> getByNombre(@PathVariable("nombre") String nombrePro) {
        if (!experienciaService.existsByNombre(nombrePro))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Experiencia experiencia = experienciaService.getByNombre(nombrePro).get();
        return new ResponseEntity<Experiencia>(experiencia, HttpStatus.OK);
    }
    */
    
    /*
    @GetMapping("")
    public ResponseEntity<List<Experiencia>> list() {
        List<Experiencia> list = experienciaService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    */
}
 
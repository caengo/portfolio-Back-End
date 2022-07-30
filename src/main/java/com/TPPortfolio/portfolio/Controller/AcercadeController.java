
package com.TPPortfolio.portfolio.Controller;

import com.TPPortfolio.portfolio.Dto.AcercadeDto;
import com.TPPortfolio.portfolio.Dto.Mensaje;
import com.TPPortfolio.portfolio.Entity.Acercade;
import com.TPPortfolio.portfolio.Entity.Persona;
import com.TPPortfolio.portfolio.Security.Entity.UsuarioPrincipal;
import com.TPPortfolio.portfolio.Service.AcercadeService;
import com.TPPortfolio.portfolio.Service.PersonaService;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Hibernate;
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
@RequestMapping("api/acercade")
@CrossOrigin
public class AcercadeController {
    
    @Autowired
    AcercadeService acercadeService;
    
    @Autowired
    PersonaService personaService;
    
     @GetMapping("{id}")
    public ResponseEntity<Acercade> getById(@PathVariable("id") int id) {
        if (!acercadeService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Acercade acercade = acercadeService.getOne(id).get();
        acercade.setIdPersona(acercade.getPersona().getId());
        return new ResponseEntity<Acercade>(acercade, HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('USER')")
    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody AcercadeDto acercadeDto) {
        if (!acercadeService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        if (!personaService.existsById(acercadeDto.getIdPersona()))
            return new ResponseEntity(new Mensaje("la persona no existe"), HttpStatus.BAD_REQUEST);
        
        int idUsuarioLogueado = GetIdUsuarioLogueado();
        int idPersonaOriginal = GetIdPersonaAcercade(id);
        if (GetIdUsuarioPersona(idPersonaOriginal) != idUsuarioLogueado)
            return new ResponseEntity(new Mensaje("el usuario logueado no tiene acceso al acercade"), HttpStatus.BAD_REQUEST);
        if (acercadeDto.getIdPersona() != idPersonaOriginal)
            return new ResponseEntity(new Mensaje("no es posible asignar la persona a otro usuario"), HttpStatus.BAD_REQUEST);

        Persona persona = new Persona();
        persona.setId(acercadeDto.getIdPersona());
        
        Acercade acercade = acercadeService.getOne(id).get();
        acercade.setAcercade(acercadeDto.getAcercade());
        acercade.setFechanac(acercadeDto.getFechanac());
        acercade.setEmail(acercadeDto.getEmail());
        acercade.setTelefono(acercadeDto.getTelefono());
        acercade.setDireccion(acercadeDto.getDireccion());
        acercade.setPersona(persona);
        acercadeService.save(acercade);
        return new ResponseEntity(new Mensaje("Acerca De actualizado"), HttpStatus.OK);

    }
    
    private int GetIdPersonaAcercade(int idAcercade){
        Acercade acercade = acercadeService.getOne(idAcercade).get();
        return acercade.getPersona().getId();
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
    @PreAuthorize("hasRole('USER')")
    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody AcercadeDto acercadeDto) {
        int idUsuarioLogueado = GetIdUsuarioLogueado();
        if (GetIdUsuarioPersona(acercadeDto.getIdPersona()) != idUsuarioLogueado)
            return new ResponseEntity(new Mensaje("el usuario logueado no tiene acceso a la persona"), HttpStatus.BAD_REQUEST);      
        if (!personaService.existsById(acercadeDto.getIdPersona()))
            return new ResponseEntity(new Mensaje("la persona no existe"), HttpStatus.BAD_REQUEST);
        Persona persona = new Persona();
        persona.setId(acercadeDto.getIdPersona());
        
        Acercade acercade = new Acercade (acercadeDto.getAcercade(),acercadeDto.getFechanac()
                ,acercadeDto.getEmail(),acercadeDto.getTelefono(),acercadeDto.getDireccion(),acercadeDto.getIdPersona());
        acercade.setPersona(persona);
        acercadeService.save(acercade);
        return new ResponseEntity(new Mensaje("Acerca De creado"), HttpStatus.OK);
    }
*/
        
    /*
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if (!acercadeService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        acercadeService.delete(id);
        return new ResponseEntity(new Mensaje("Acerca De eliminado"), HttpStatus.OK);
    }
    */
}

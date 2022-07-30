
package com.TPPortfolio.portfolio.Controller;



import com.TPPortfolio.portfolio.Dto.Mensaje;
import com.TPPortfolio.portfolio.Dto.PersonaDto;
import com.TPPortfolio.portfolio.Entity.Persona;
import com.TPPortfolio.portfolio.Entity.Acercade;
import com.TPPortfolio.portfolio.Entity.Experiencia;
import com.TPPortfolio.portfolio.Security.Entity.Usuario;
import com.TPPortfolio.portfolio.Security.Entity.UsuarioPrincipal;
import com.TPPortfolio.portfolio.Security.Service.UsuarioService;
import com.TPPortfolio.portfolio.Service.PersonaService;
import com.TPPortfolio.portfolio.Service.AcercadeService;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Hibernate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("api/personas")
@CrossOrigin(origins = "http://localhost:4200") 
public class PersonaController {

    @Autowired
    PersonaService personaService;
    @Autowired
    AcercadeService acercadeService;
    @Autowired
    UsuarioService usuarioService;
    
    @GetMapping("")
    public ResponseEntity<Persona> getByUser(@RequestParam(name="userId", required=true) int userId) {
        Usuario user = usuarioService.getOne(userId).get();
        if (user == null)
            return new ResponseEntity(new Mensaje("user no existe"), HttpStatus.NOT_FOUND);
        Persona persona = user.getPersona();
        if (persona == null)
            return new ResponseEntity(new Mensaje("user no tiene persona"), HttpStatus.NOT_FOUND);
        persona.setIdUsuario(userId);
        return new ResponseEntity<Persona>(persona, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Persona> getById(@PathVariable("id") int id) {
        if (!personaService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Persona persona = personaService.getOne(id).get();
        persona.setIdUsuario(persona.getUsuario().getId());
        return new ResponseEntity<Persona>(persona, HttpStatus.OK);
    }
    
    @GetMapping("{idPersona}/acercade")
    public ResponseEntity<Acercade> getAcercade(@PathVariable("idPersona") int idPersona) {
        if (!personaService.existsById(idPersona))
            return new ResponseEntity(new Mensaje("persona no existe"), HttpStatus.BAD_REQUEST);
        Persona persona = personaService.getOne(idPersona).get();
        Acercade acercade = persona.getAcercade();
        if (acercade == null)
            return new ResponseEntity(new Mensaje("acercade no existe"), HttpStatus.NOT_FOUND);
        acercade.setIdPersona(persona.getId());
        return new ResponseEntity<Acercade>(acercade, HttpStatus.OK);
    }
    
    @GetMapping("{idPersona}/experiencias")
    public ResponseEntity<List<Experiencia>> getExperiencias(@PathVariable("idPersona") int idPersona) {
        if (!personaService.existsById(idPersona))
            return new ResponseEntity(new Mensaje("persona no existe"), HttpStatus.BAD_REQUEST);
        Persona persona = personaService.getOne(idPersona).get();
        List<Experiencia> experiencias = persona.getExperiencias();
        experiencias.forEach(exp -> exp.setIdPersona(persona.getId()));
        return new ResponseEntity<List<Experiencia>>(experiencias, HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('USER')")
    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody PersonaDto personaDto) {
        if (!personaService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        int idUsuarioLogueado = GetIdUsuarioLogueado();
        if (GetIdUsuarioPersona(id) != idUsuarioLogueado)
            return new ResponseEntity(new Mensaje("el usuario logueado no tiene acceso a la persona"), HttpStatus.BAD_REQUEST);
        if (personaDto.getIdUsuario() != idUsuarioLogueado)
            return new ResponseEntity(new Mensaje("no es posible asignar la persona a otro usuario"), HttpStatus.BAD_REQUEST);
        if (!usuarioService.existsById(personaDto.getIdUsuario()))
            return new ResponseEntity(new Mensaje("el usuario no existe"), HttpStatus.BAD_REQUEST);
        Usuario user = new Usuario();
        user.setId(personaDto.getIdUsuario());
        
        Persona persona = personaService.getOne(id).get();
        persona.setNombre(personaDto.getNombre());
        persona.setApellido(personaDto.getApellido());
        persona.setProfesion(personaDto.getProfesion());
        persona.setResenia(personaDto.getResenia());
        persona.setImagen(personaDto.getImagen());
        persona.setUsuario(user);
        personaService.save(persona);
        return new ResponseEntity(new Mensaje("persona actualizada"), HttpStatus.OK);

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
    
        /*@PreAuthorize("hasRole('ADMIN')") //Autorizacion de Administrador para Crear
    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody PersonaDto personaDto) {
        if (StringUtils.isBlank(personaDto.getNombre()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if (StringUtils.isBlank(personaDto.getApellido()))
            return new ResponseEntity(new Mensaje("el apellido es obligatorio"), HttpStatus.BAD_REQUEST);
        if (StringUtils.isBlank(personaDto.getProfesion()))
            return new ResponseEntity(new Mensaje("la profesión es obligatorio"), HttpStatus.BAD_REQUEST);
        if (StringUtils.isBlank(personaDto.getResenia()))
            return new ResponseEntity(new Mensaje("la resenia es obligatorio"), HttpStatus.BAD_REQUEST);
        if (StringUtils.isBlank(personaDto.getImagen()))
            return new ResponseEntity(new Mensaje("la url de la imagen es obligatorio"), HttpStatus.BAD_REQUEST);

        Persona persona = new Persona (personaDto.getNombre(),personaDto.getApellido(),personaDto.getProfesion(),personaDto.getResenia()
                ,personaDto.getImagen(), personaDto.getIdUsuario());
        personaService.save(persona);
        return new ResponseEntity(new Mensaje("persona creada"), HttpStatus.OK);
    }
    */
    
     /*@PreAuthorize("hasRole('ADMIN')")*/ //Autorizacion de Administrador para Eliminar
    /*
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if (!personaService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        personaService.delete(id);
        return new ResponseEntity(new Mensaje("persona eliminada"), HttpStatus.OK);
    }
    */
}


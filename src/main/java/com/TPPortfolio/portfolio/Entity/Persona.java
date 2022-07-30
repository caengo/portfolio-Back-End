
package com.TPPortfolio.portfolio.Entity;

import com.TPPortfolio.portfolio.Security.Entity.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
public class Persona {
   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private String apellido;
    private String profesion;
    private String resenia;
    private String imagen;
    
    @Transient
    private int idUsuario;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;
    
    
    @JsonIgnore
    @OneToOne(mappedBy = "persona")
    private Acercade acercade;
    

    @JsonIgnore
    @OneToMany(mappedBy = "persona")
    private List<Experiencia> experiencias;
    
    public Persona() {
        this.nombre = "";
        this.apellido = "";
        this.profesion = "";
        this.resenia = "";
        this.imagen = "";
    }

    public Persona(String nombre, String apellido, String profesion, String resenia, String imagen, int idUsuario) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.profesion = profesion;
        this.resenia = resenia;
        this.imagen = imagen;
        this.idUsuario = idUsuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public String getResenia() {
        return resenia;
    }

    public void setResenia(String resenia) {
        this.resenia = resenia;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    
    public Acercade getAcercade() {
        return acercade;
    }

    public void setAcercade(Acercade acercade) {
        this.acercade = acercade;
    }
    

    public List<Experiencia> getExperiencias() {
        return experiencias;
    }

    public void setExperiencias(List<Experiencia> experiencias) {
        this.experiencias = experiencias;
    }

}


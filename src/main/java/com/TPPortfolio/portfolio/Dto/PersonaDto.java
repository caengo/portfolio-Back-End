
package com.TPPortfolio.portfolio.Dto;

import javax.validation.constraints.NotBlank;


public class PersonaDto {

    private String nombre;

    private String apellido;

    private String profesion;

    private String resenia;   

    private String imagen;
    
    @NotBlank
    private int idUsuario;
   

    public PersonaDto() {
    }

    public PersonaDto(@NotBlank String nombre,
                        @NotBlank String apellido,
                        @NotBlank String profesion,
                        @NotBlank String resenia,
                        @NotBlank String imagen,
                        @NotBlank int idUsuario) {
        
        this.apellido = apellido;
        this.profesion = profesion;
        this.resenia = resenia;
        this.imagen = imagen;
        this.idUsuario = idUsuario;   
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

    

}

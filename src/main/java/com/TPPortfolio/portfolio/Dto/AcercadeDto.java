
package com.TPPortfolio.portfolio.Dto;

import javax.validation.constraints.NotBlank;


public class AcercadeDto {
    
    private String acercade;
    private String fechanac;
    private String email;
    private String telefono;
    private String direccion;


    
    @NotBlank
    private int idPersona;

    public AcercadeDto() {
    }

    public AcercadeDto(String acercade, String fechanac, String email, String telefono, String direccion, int idPersona) {
        this.acercade = acercade;
        this.fechanac = fechanac;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
        this.idPersona = idPersona;
    }

    public String getAcercade() {
        return acercade;
    }

    public void setAcercade(String acercade) {
        this.acercade = acercade;
    }

    public String getFechanac() {
        return fechanac;
    }

    public void setFechanac(String fechanac) {
        this.fechanac = fechanac;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
     
    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }
}

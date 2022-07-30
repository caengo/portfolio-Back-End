
package com.TPPortfolio.portfolio.Dto;

import javax.validation.constraints.NotBlank;


public class ExperienciaDto {
    
    private String fechaInicio;
    private String fechaFinal;
    private String nombre;
    private String resenia;
    private String cargo;
    private String descripcion;
    private String referencia;
    
      @NotBlank
    private int idPersona;
    
    public ExperienciaDto() {
    }

    public ExperienciaDto(String fechaInicio, String fechaFinal, String nombre, String resenia, String cargo, String descripcion, String referencia, int idPersona) {
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.nombre = nombre;
        this.resenia = resenia;
        this.cargo = cargo;
        this.descripcion = descripcion;
        this.referencia = referencia;
        this.idPersona = idPersona;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(String fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getResenia() {
        return resenia;
    }

    public void setResenia(String resenia) {
        this.resenia = resenia;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }
    
    
    
}

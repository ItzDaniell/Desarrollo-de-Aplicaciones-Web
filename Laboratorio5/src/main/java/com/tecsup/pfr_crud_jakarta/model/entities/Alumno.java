package com.tecsup.pfr_crud_jakarta.model.entities;

import java.util.Date;

public class Alumno {
    private String codigo;
    private String nombre;
    private String apellido;
    private Date fecha_nacimiento;
    private String sexo;
    public Alumno(String codigo, String nombre, String apellido, Date fecha_nacimiento, String sexo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha_nacimiento = fecha_nacimiento;
        this.sexo = sexo;
    }
    public Alumno() {
    }
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
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
    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }
    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }
    public String getSexo() {
        return sexo;
    }
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    @Override
    public String toString() {
        return "Alumno{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", fecha_nacimiento=" + fecha_nacimiento +
                ", sexo='" + sexo + '\'' +
                '}';
    }
}

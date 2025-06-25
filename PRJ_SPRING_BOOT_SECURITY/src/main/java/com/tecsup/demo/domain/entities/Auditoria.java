package com.tecsup.demo.domain.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "auditoria")
public class Auditoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    private String tabla;
    @Column(name = "id_registro")
    private Integer idRegistro;
    private Date fecha;
    private String usuario;
    private String tipo;

    public Auditoria() {
    }

    public Auditoria(String tabla, Integer idRegistro, Date fecha,
                     String usuario, String tipo) {
        super();
        this.tabla = tabla;
        this.idRegistro = idRegistro;
        this.fecha = fecha;
        this.usuario = usuario;
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Auditoria [id=" + id + ", tabla=" + tabla + ", idRegistro="
                + idRegistro + ", fecha=" + fecha + ", usuario=" + usuario
                + ", tipo=" + tipo + "]";
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getTabla() {
        return tabla;
    }
    public void setTabla(String tabla) {
        this.tabla = tabla;
    }
    public Integer getIdRegistro() {
        return idRegistro;
    }
    public void setIdRegistro(Integer idRegistro) {
        this.idRegistro = idRegistro;
    }
    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
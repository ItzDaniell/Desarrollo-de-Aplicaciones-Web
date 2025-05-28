package com.tecsup.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class OrdenCompra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long NroOrdenC;
    private LocalDateTime fechaEmision;
    private String situacion;
    private double total;
    @ManyToOne
    @JoinColumn(name = "codLab", nullable = false)
    private Laboratorio laboratorio;
    private int NroFacturaProv;

    public long getNroOrdenC() {
        return NroOrdenC;
    }

    public void setNroOrdenC(long nroOrdenC) {
        NroOrdenC = nroOrdenC;
    }

    public LocalDateTime getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDateTime fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getSituacion() {
        return situacion;
    }

    public void setSituacion(String situacion) {
        this.situacion = situacion;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Laboratorio getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(Laboratorio laboratorio) {
        this.laboratorio = laboratorio;
    }

    public int getNroFacturaProv() {
        return NroFacturaProv;
    }

    public void setNroFacturaProv(int nroFacturaProv) {
        NroFacturaProv = nroFacturaProv;
    }
}

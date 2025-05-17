package com.tecsup.practica2.model;
import java.time.LocalDate;

public class OrdenCompra {
    private Long nroOrdenC;
    private LocalDate fechaEmision;
    private String situacion;
    private double total;
    private String nroFacturaProv;
    private Laboratorio laboratorio;

    public OrdenCompra() {
    }

    public OrdenCompra(Long nroOrdenC, LocalDate fechaEmision, String situacion, double total, String nroFacturaProv, Laboratorio laboratorio) {
        this.nroOrdenC = nroOrdenC;
        this.fechaEmision = fechaEmision;
        this.situacion = situacion;
        this.total = total;
        this.nroFacturaProv = nroFacturaProv;
        this.laboratorio = laboratorio;
    }

    public Long getNroOrdenC() {
        return nroOrdenC;
    }

    public void setNroOrdenC(Long nroOrdenC) {
        this.nroOrdenC = nroOrdenC;
    }

    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDate fechaEmision) {
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

    public String getNroFacturaProv() {
        return nroFacturaProv;
    }

    public void setNroFacturaProv(String nroFacturaProv) {
        this.nroFacturaProv = nroFacturaProv;
    }

    public Laboratorio getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(Laboratorio laboratorio) {
        this.laboratorio = laboratorio;
    }
}

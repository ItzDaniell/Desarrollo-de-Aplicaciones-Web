package com.tecsup.practica2.controller;

import com.tecsup.practica2.model.Laboratorio;
import com.tecsup.practica2.model.OrdenCompra;
import com.tecsup.practica2.service.LaboratorioService;
import com.tecsup.practica2.service.OrdenCompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/ordencompra")
public class OrdenCompraController {

    private final OrdenCompraService ordenCompraService;
    private final LaboratorioService laboratorioService;

    @Autowired
    public OrdenCompraController(OrdenCompraService ordenCompraService, LaboratorioService laboratorioService) {
        this.ordenCompraService = ordenCompraService;
        this.laboratorioService = laboratorioService;
    }

    @GetMapping
    public List<OrdenCompra> listar() {
        return ordenCompraService.listar();
    }

    @GetMapping("/{id}")
    public OrdenCompra buscarPorId(@PathVariable Long id) {
        return ordenCompraService.buscarPorId(id);
    }

    @PostMapping
    public void guardar(@RequestBody OrdenCompraRequest request) {
        Laboratorio laboratorio = laboratorioService.buscarPorId(request.getLaboratorioId());
        if (laboratorio == null) {
            throw new RuntimeException("Laboratorio con ID " + request.getLaboratorioId() + " no existe.");
        }

        OrdenCompra orden = new OrdenCompra();
        orden.setFechaEmision(LocalDate.parse(request.getFechaEmision()));
        orden.setSituacion(request.getSituacion());
        orden.setTotal(request.getTotal());
        orden.setNroFacturaProv(request.getNroFacturaProv());
        orden.setLaboratorio(laboratorio);

        ordenCompraService.guardar(orden);
    }

    @PutMapping("/{id}")
    public void actualizar(@PathVariable Long id, @RequestBody OrdenCompraRequest request) {
        Laboratorio laboratorio = laboratorioService.buscarPorId(request.getLaboratorioId());
        if (laboratorio == null) {
            throw new RuntimeException("Laboratorio con ID " + request.getLaboratorioId() + " no existe.");
        }

        OrdenCompra orden = new OrdenCompra();
        orden.setNroOrdenC(id);
        orden.setFechaEmision(LocalDate.parse(request.getFechaEmision()));
        orden.setSituacion(request.getSituacion());
        orden.setTotal(request.getTotal());
        orden.setNroFacturaProv(request.getNroFacturaProv());
        orden.setLaboratorio(laboratorio);

        ordenCompraService.actualizar(orden);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        ordenCompraService.eliminar(id);
    }
    
    public static class OrdenCompraRequest {
        private String fechaEmision;
        private String situacion;
        private double total;
        private String nroFacturaProv;
        private Long laboratorioId;

        public String getFechaEmision() {
            return fechaEmision;
        }

        public void setFechaEmision(String fechaEmision) {
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

        public Long getLaboratorioId() {
            return laboratorioId;
        }

        public void setLaboratorioId(Long laboratorioId) {
            this.laboratorioId = laboratorioId;
        }
    }
}

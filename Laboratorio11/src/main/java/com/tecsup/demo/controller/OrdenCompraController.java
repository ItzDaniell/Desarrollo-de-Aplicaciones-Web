package com.tecsup.demo.controller;

import com.tecsup.demo.model.Laboratorio;
import com.tecsup.demo.model.OrdenCompra;
import com.tecsup.demo.repository.LaboratorioRepository;
import com.tecsup.demo.repository.OrdenCompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/ordenes")
public class OrdenCompraController {

    @Autowired
    private OrdenCompraRepository OrdenCompraRepository;

    @Autowired
    private LaboratorioRepository laboratorioRepository;

    @GetMapping
    public List<OrdenCompra> findAll() {
        return OrdenCompraRepository.findAll();
    }

    @GetMapping("/{id}")
    public OrdenCompra findById(@PathVariable long id) {
        return OrdenCompraRepository.findById(id).get();
    }

    @PostMapping
    public OrdenCompra save(@RequestBody OrdenCompra orden) {
        Long labId = orden.getLaboratorio().getCodLab();

        Laboratorio laboratorio = laboratorioRepository.findById(labId)
                .orElseThrow(() -> new RuntimeException("Laboratorio no encontrado"));

        orden.setLaboratorio(laboratorio);

        return OrdenCompraRepository.save(orden);
    }

    @PutMapping("/{id}")
    public OrdenCompra update(@PathVariable long id, @RequestBody OrdenCompra orden) {
        orden.setNroOrdenC(id);
        return OrdenCompraRepository.save(orden);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        OrdenCompraRepository.deleteById(id);
    }
}

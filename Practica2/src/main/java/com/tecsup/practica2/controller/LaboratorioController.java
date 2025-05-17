package com.tecsup.practica2.controller;

import com.tecsup.practica2.model.Laboratorio;
import com.tecsup.practica2.service.LaboratorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/laboratorio")
public class LaboratorioController {

    private final LaboratorioService laboratorioService;

    @Autowired
    public LaboratorioController(LaboratorioService laboratorioService) {
        this.laboratorioService = laboratorioService;
    }

    @GetMapping
    public List<Laboratorio> listar() {
        return laboratorioService.listar();
    }

    @GetMapping("/{id}")
    public Laboratorio buscarPorId(@PathVariable Long id) {
        return laboratorioService.buscarPorId(id);
    }

    @PostMapping
    public void guardar(@RequestBody Laboratorio laboratorio) {
        laboratorioService.guardar(laboratorio);
    }

    @PutMapping("/{id}")
    public void actualizar(@PathVariable Long id, @RequestBody Laboratorio laboratorio) {
        laboratorio.setCodLab(id);
        laboratorioService.actualizar(laboratorio);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        laboratorioService.eliminar(id);
    }
}
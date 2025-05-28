package com.tecsup.demo.controller;

import com.tecsup.demo.model.Laboratorio;
import com.tecsup.demo.repository.LaboratorioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/laboratorio")
public class LaboratorioController {
    @Autowired
    private LaboratorioRepository repository;

    @GetMapping
    public List<Laboratorio> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Laboratorio findById(@PathVariable long id) {
        return repository.findById(id).get();
    }

    @PostMapping
    public Laboratorio save(@RequestBody Laboratorio laboratorio) {
        return repository.save(laboratorio);
    }

    @PutMapping("/{id}")
    public Laboratorio update(@PathVariable Long id, @RequestBody Laboratorio laboratorio) {
        laboratorio.setCodLab(id);
        return repository.save(laboratorio);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        repository.deleteById(id);
    }
}

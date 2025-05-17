package com.tecsup.demospring1.controller;

import com.tecsup.demospring1.model.Curso;
import com.tecsup.demospring1.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    private final CursoService cursoService;

    @Autowired
    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping
    public List<Curso> listar() {
        return cursoService.listar();
    }

    @GetMapping("/{id}")
    public Curso buscarPorId(@PathVariable Long id) {
        return cursoService.buscarPorId(id);
    }

    @PostMapping
    public void guardar(@RequestBody Curso curso) {
        cursoService.guardar(curso);
    }

    @PutMapping("/{id}")
    public void actualizar(@PathVariable Long id, @RequestBody Curso curso) {
        curso.setId(id);
        cursoService.actualizar(curso);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        cursoService.eliminar(id);
    }
}
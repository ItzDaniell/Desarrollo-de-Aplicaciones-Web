package com.tecsup.laboratorio9.controller;

import com.tecsup.laboratorio9.model.Alumno;
import com.tecsup.laboratorio9.service.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alumnos")
public class AlumnoController {

    private final AlumnoService alumnoService;

    @Autowired
    public AlumnoController(AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

    @GetMapping
    public List<Alumno> listar() {
        return alumnoService.listar();
    }

    @GetMapping("/{id}")
    public Alumno buscarPorId(@PathVariable int id) {
        return alumnoService.buscarPorId(id);
    }

    @PostMapping
    public void guardar(@RequestBody Alumno alumno) {
        alumnoService.guardar(alumno);
    }

    @PutMapping("/{id}")
    public void actualizar(@PathVariable int id, @RequestBody Alumno alumno) {
        alumno.setCodigo(id);
        alumnoService.actualizar(alumno);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable int id) {
        alumnoService.eliminar(id);
    }
}

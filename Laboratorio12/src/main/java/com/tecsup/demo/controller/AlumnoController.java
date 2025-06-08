package com.tecsup.demo.controller;

import com.tecsup.demo.model.Alumno;
import com.tecsup.demo.repository.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AlumnoController {

    @Autowired
    private AlumnoRepository repository;

    @GetMapping("/")
    public String listar(Model model) {
        model.addAttribute("alumnos", repository.findAll());
        return "lista";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("alumno", new Alumno());
        return "formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Alumno alumno) {
        repository.save(alumno);
        return "redirect:/";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable int id, Model model) {
        Alumno alumno = repository.findById(id).orElse(null);
        if (alumno != null) {
            model.addAttribute("alumno", alumno);
            return "formulario";
        }
        return "redirect:/";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable int id) {
        repository.deleteById(id);
        return "redirect:/";
    }
}

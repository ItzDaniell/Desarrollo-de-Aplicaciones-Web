package com.tecsup.demo.controller;

import com.tecsup.demo.model.documents.Alumno;
import com.tecsup.demo.model.documents.Curso;
import com.tecsup.demo.service.AlumnoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Map;

@Controller
@SessionAttributes("alumno")
public class AlumnoController {

    @Autowired
    private AlumnoService servicio;

    @RequestMapping(value = "/listarAlumno", method = RequestMethod.GET)
    public String listar(Model model) {
        model.addAttribute("titulo", "Listado de Alumnos");
        model.addAttribute("alumnos", servicio.listar());
        return "listarView";
    }

    @RequestMapping(value = "/buscarAlumno/{id}", method = RequestMethod.GET)
    public String buscar(@PathVariable String id, Model model) {
        model.addAttribute("titulo", "Listado de Alumnos");
        model.addAttribute("alumno", servicio.buscar(id));
        return "buscarView";
    }

    @RequestMapping(value = "/formAlumno")
    public String crear(Map<String, Object> model) {

        Alumno alumno = new Alumno();
        model.put("alumno", alumno);
        model.put("titulo", "Formulario de Alumno");
        return "formView";
    }

    @RequestMapping(value = "/formCurso/{id}")
    public String editar(@PathVariable(value = "id") String id, Map<String, Object> model) {

        Curso curso = null;

        if (id!=null && id.length()>0) {
            curso = servicio.buscar(id);
        } else {
            return "redirect:/listarCurso";
        }
        model.put("curso", curso);
        model.put("titulo", "Editar Curso");
        return "formView";
    }

    @RequestMapping(value = "/formCurso", method = RequestMethod.POST)
    public String guardar(@Valid Curso curso, BindingResult result, Model model, SessionStatus status) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario de Curso");
            return "formView";
        }
        //System.out.println(curso);
        servicio.grabar(curso);
        status.setComplete();
        return "redirect:/listarAlumno";
    }

    @RequestMapping(value = "/eliminarAlumno/{id}")
    public String eliminar(@PathVariable(value = "id") String id) {
        if (id!=null && id.length()>0) {
            servicio.eliminar(id);
        }
        return "redirect:/listarAlumno";
    }
}

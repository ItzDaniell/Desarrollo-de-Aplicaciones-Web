package com.tecsup.laboratorio92.controller;

import com.tecsup.laboratorio92.model.entities.Alumno;
import com.tecsup.laboratorio92.service.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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

    @RequestMapping(value = "/listar", method = RequestMethod.GET)
    public String listar(Model model) {
        model.addAttribute("titulo", "Listado de Cursos  CJAVA");
        model.addAttribute("alumnos", servicio.listar());
        return "listarView";
    }

    @RequestMapping(value = "/form")
    public String crear(Map<String, Object> model) {
        Alumno alumno = new Alumno();
        model.put("alumno", alumno);
        model.put("titulo", "Formulario de Alumno");
        return "formView";
    }

    @RequestMapping(value = "/form/{id}")
    public String editar(@PathVariable(value = "id") Integer id, Map<String, Object> model) {

        Alumno alumno = null;

        if (id > 0) {
            alumno = servicio.buscar(id);
        } else {
            return "redirect:/listar";
        }
        model.put("alumno", alumno);
        model.put("titulo", "Editar Alumno");
        return "formView";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String guardar(@Validated Alumno alumno, BindingResult result, Model model, SessionStatus status) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario de Alumno");
            return "formView";
        }
        //System.out.println(alumno);
        servicio.grabar(alumno);
        status.setComplete();
        return "redirect:listar";
    }

    @RequestMapping(value = "/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id") Integer id) {

        if (id > 0) {
            servicio.eliminar(id);
        }
        return "redirect:/listar";
    }
}
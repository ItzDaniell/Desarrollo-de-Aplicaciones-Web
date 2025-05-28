package com.tecsup.demo.controller;
import com.tecsup.demo.model.documents.Curso;
import com.tecsup.demo.service.CursoService;
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
@SessionAttributes("curso")
public class CursoController {

    @Autowired
    private CursoService servicio;

    @RequestMapping(value = "/listarCurso", method = RequestMethod.GET)
    public String listar(Model model) {
        model.addAttribute("titulo", "Listado de cursos");
        model.addAttribute("cursos", servicio.listar());
        return "listarView";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String listarFull(Model model) {
        model.addAttribute("titulo", "Listado de cursos");
        model.addAttribute("cursos", servicio.listar());
        return "listarView";
    }

    @RequestMapping(value = "/buscarCurso/{id}", method = RequestMethod.GET)
    public String buscar(@PathVariable String id, Model model) {
        model.addAttribute("titulo", "Listado de cursos");
        model.addAttribute("curso", servicio.buscar(id));
        return "buscarView";
    }

    @RequestMapping(value = "/formCurso")
    public String crear(Map<String, Object> model) {

        Curso curso = new Curso();
        model.put("curso", curso);
        model.put("titulo", "Formulario de Curso");
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
        return "redirect:listarCurso";
    }

    @RequestMapping(value = "/eliminarCurso/{id}")
    public String eliminar(@PathVariable(value = "id") String id) {

        if (id!=null && id.length()>0) {
            servicio.eliminar(id);
        }
        return "redirect:/listarCurso";
    }
}
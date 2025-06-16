package com.tecsup.laboratorio13.controllers;

import com.tecsup.laboratorio13.entities.Categoria;
import com.tecsup.laboratorio13.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @PostMapping
    public Categoria create(@RequestBody Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @GetMapping
    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Categoria findById(@PathVariable Long id) {
        return categoriaRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id, @RequestBody Categoria categoria) {
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(id);
        if (categoriaOptional.isPresent()) {
            Categoria c = categoriaOptional.get();
            c.setNombre(categoria.getNombre());
            categoriaRepository.save(c);
            return "Categoria Actualizada";
        } else {
            return "Categoria no encontrada";
        }
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(id);
        if (categoriaOptional.isPresent()) {
            categoriaRepository.deleteById(id);
            return "Categoria eliminada";
        } else {
            return "Categoria no encontrada";
        }
    }
}

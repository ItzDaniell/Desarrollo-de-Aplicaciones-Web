package com.tecsup.demo.controllers;

import com.tecsup.demo.exceptions.ResourceNotFoundException;
import com.tecsup.demo.models.Categoria;
import com.tecsup.demo.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:5173")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping("/categorias")
    public List<Categoria> listaCategorias() {
        return categoriaRepository.findAll();
    }

    @PostMapping("/categorias")
    public Categoria guardarCategoria(@RequestBody Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @GetMapping("/categoria/{id}")
    public ResponseEntity<Categoria> obtenerCategoriaPorId(@PathVariable Long id){
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe la categoria con el ID : " + id));
        return ResponseEntity.ok(categoria);
    }

    @PutMapping("/categoria/{id}")
    public ResponseEntity<Categoria> actualizarCategoria(@PathVariable Long id,@RequestBody Categoria detallesCategoria){
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe la categoria con el ID : " + id));

        categoria.setNombre(detallesCategoria.getNombre());

        Categoria categoriaActualizado = categoriaRepository.save(categoria);
        return ResponseEntity.ok(categoriaActualizado);
    }

    @DeleteMapping("/categoria/{id}")
    public ResponseEntity<Map<String,Boolean>> eliminarCategoria(@PathVariable Long id){
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe la categoria con el ID : " + id));

        categoriaRepository.delete(categoria);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminar",Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
}

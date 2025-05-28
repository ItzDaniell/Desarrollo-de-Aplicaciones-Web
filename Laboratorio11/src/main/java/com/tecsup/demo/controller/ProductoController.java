package com.tecsup.demo.controller;

import com.tecsup.demo.model.Producto;
import com.tecsup.demo.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/producto")
public class ProductoController {

    @Autowired
    private ProductoRepository repository;

    @GetMapping
    public List<Producto> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Producto findById(@PathVariable long id) {
        return repository.findById(id).get();
    }

    @PostMapping
    public Producto save(@RequestBody Producto producto) {
        return repository.save(producto);
    }

    @PutMapping("/{id}")
    public Producto update(@PathVariable Long id, @RequestBody Producto producto) {
        producto.setId(id);
        return repository.save(producto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        repository.deleteById(id);
    }
}

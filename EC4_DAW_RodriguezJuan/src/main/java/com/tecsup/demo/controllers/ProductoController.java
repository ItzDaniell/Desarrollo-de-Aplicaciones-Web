package com.tecsup.demo.controllers;

import com.tecsup.demo.domain.entities.Producto;
import com.tecsup.demo.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*") // Permite acceso desde frontend (React)
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    // ✅ Listar productos (Dueño y Cliente)
    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_DUENO', 'ROLE_CLIENTE')")
    public List<Producto> listar() {
        return productoService.listar();
    }

    // ✅ Ver producto por ID (Dueño y Cliente)
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_DUENO', 'ROLE_CLIENTE')")
    public Producto ver(@PathVariable Long id) {
        return productoService.buscar(id);
    }

    // ✅ Crear producto (solo Dueño)
    @PostMapping
    @PreAuthorize("hasRole('ROLE_DUENO')")
    public Producto crear(@RequestBody Producto producto) {
        return productoService.grabar(producto);
    }

    // ✅ Actualizar producto (solo Dueño)
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_DUENO')")
    public Producto actualizar(@PathVariable Long id, @RequestBody Producto producto) {
        Producto existente = productoService.buscar(id);
        if (existente == null) {
            throw new RuntimeException("Producto no encontrado con ID: " + id);
        }

        existente.setNombre(producto.getNombre());
        existente.setDescripcion(producto.getDescripcion());
        existente.setPrecio(producto.getPrecio());
        // agrega otros campos según tu entidad

        return productoService.grabar(existente);
    }

    // ✅ Eliminar producto (solo Dueño)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_DUENO')")
    public void eliminar(@PathVariable Long id) {
        productoService.eliminar(id);
    }
}

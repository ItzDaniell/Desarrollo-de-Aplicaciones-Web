package com.tecsup.demo.controllers;

import com.tecsup.demo.domain.entities.Producto;
import com.tecsup.demo.services.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;


@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Productos", description = "Operaciones CRUD para productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_DUENO', 'ROLE_CLIENTE')")
    @Operation(summary = "Listar todos los productos (Dueño y Cliente)")
    public List<Producto> listar() {
        return productoService.listar();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_DUENO', 'ROLE_CLIENTE')")
    @Operation(summary = "Ver un producto por ID (Dueño y Cliente)")
    public Producto ver(@PathVariable Long id) {
        return productoService.buscar(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_DUENO')")
    @Operation(summary = "Crear un producto (Solo Dueño)")
    public Producto crear(@RequestBody Producto producto) {
        return productoService.grabar(producto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_DUENO')")
    @Operation(summary = "Actualizar un producto por ID (Solo Dueño)")
    public Producto actualizar(@PathVariable Long id, @RequestBody Producto producto) {
        Producto existente = productoService.buscar(id);
        if (existente == null) {
            throw new RuntimeException("Producto no encontrado con ID: " + id);
        }

        existente.setNombre(producto.getNombre());
        existente.setDescripcion(producto.getDescripcion());
        existente.setPrecio(producto.getPrecio());
        return productoService.grabar(existente);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_DUENO')")
    @Operation(summary = "Eliminar un producto por ID (Solo Dueño)")
    public void eliminar(@PathVariable Long id) {
        productoService.eliminar(id);
    }
}

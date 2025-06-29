package com.tecsup.demo.controllers;

import com.tecsup.demo.exceptions.ResourceNotFoundException;
import com.tecsup.demo.models.Categoria;
import com.tecsup.demo.models.Producto;
import com.tecsup.demo.repositories.CategoriaRepository;
import com.tecsup.demo.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:5173")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping("/productos")
    public List<Producto> listaProductos() {
        return productoRepository.findAll();
    }

    @PostMapping("/productos")
    public Producto crearProducto(@RequestBody Producto producto) {
        if (producto.getCategoria() != null && producto.getCategoria().getId() != null) {
            Categoria categoria = categoriaRepository.findById(producto.getCategoria().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con ID: " + producto.getCategoria().getId()));
            producto.setCategoria(categoria);
        } else {
            throw new RuntimeException("El ID de la categoría es obligatorio.");
        }
        return productoRepository.save(producto);
    }

    @GetMapping("/producto/{id}")
    public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable Long id){
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe la producto con el ID : " + id));
        return ResponseEntity.ok(producto);
    }

    @PutMapping("/producto/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id,@RequestBody Producto detallesProducto){
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el producto con el ID : " + id));

        producto.setNombre(detallesProducto.getNombre());
        producto.setDescripcion(detallesProducto.getDescripcion());
        producto.setStock(detallesProducto.getStock());
        producto.setPrecio(detallesProducto.getPrecio());

        if (detallesProducto.getCategoria() != null && detallesProducto.getCategoria().getId() != null) {
            Categoria categoria = categoriaRepository.findById(detallesProducto.getCategoria().getId())
                    .orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + detallesProducto.getCategoria().getId()));
            producto.setCategoria(categoria);
        }

        Producto productoActualizado = productoRepository.save(producto);
        return ResponseEntity.ok(productoActualizado);
    }

    //este metodo sirve para eliminar un producto
    @DeleteMapping("/producto/{id}")
    public ResponseEntity<Map<String,Boolean>> eliminarProducto(@PathVariable Long id){
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el producto con el ID : " + id));

        productoRepository.delete(producto);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminar",Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
}

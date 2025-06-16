package com.tecsup.laboratorio13.controllers;

import com.tecsup.laboratorio13.entities.Categoria;
import com.tecsup.laboratorio13.entities.Producto;
import com.tecsup.laboratorio13.entities.Tag;
import com.tecsup.laboratorio13.repositories.CategoriaRepository;
import com.tecsup.laboratorio13.repositories.ProductoRepository;
import com.tecsup.laboratorio13.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private TagRepository tagRepository;

    @PostMapping
    public String crearProducto(@RequestBody Producto producto) {
        if (producto.getCategoria() == null || !categoriaRepository.existsById(producto.getCategoria().getId())) {
            throw new IllegalArgumentException("Categoria no existe");
        }
        Categoria categoria = categoriaRepository.findById(producto.getCategoria().getId()).get();
        producto.setCategoria(categoria);

        if (producto.getTags() != null && !producto.getTags().isEmpty()) {
            List<Long> tagIds = producto.getTags().stream()
                    .map(Tag::getId)
                    .toList();
            List<Tag> tags = tagRepository.findAllById(tagIds);

            if (tags.size() != tagIds.size()) {
                throw new IllegalArgumentException("Uno o más tags no existen");
            }
            producto.setTags(tags);
        }
        productoRepository.save(producto);
        return "Producto Guardado";
    }

    @GetMapping
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Producto findById(@PathVariable Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public String updateProducto(@PathVariable Long id, @RequestBody Producto productoActualizado) {
        return productoRepository.findById(id).map(producto -> {
            producto.setNombre(productoActualizado.getNombre());
            producto.setPrecio(productoActualizado.getPrecio());
            if (productoActualizado.getCategoria() != null) {
                Optional<Categoria> categoria = categoriaRepository.findById(productoActualizado.getCategoria().getId());
                categoria.ifPresent(producto::setCategoria);
            }
            if (productoActualizado.getTags() != null) {
                List<Long> tagIds = productoActualizado.getTags()
                        .stream()
                        .map(Tag::getId)
                        .toList();
                List<Tag> tags = tagRepository.findAllById(tagIds);
            }
            productoRepository.save(producto);
            return "Producto Actualizado";
        }).orElse("Producto no encontrado");
    }

    @DeleteMapping("/{id}")
    public String deleteProducto(@PathVariable Long id) {
        if (productoRepository.findById(id).isPresent()) {
            productoRepository.deleteById(id);
            return "Producto eliminado";
        } else {
            return "Producto no encontrado";
        }
    }
}

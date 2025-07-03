package com.tecsup.demo.services;

import com.tecsup.demo.domain.entities.Producto;
import com.tecsup.demo.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public Producto grabar(Producto producto) {
        return productoRepository.save(producto); // ← retorna el producto guardado
    }

    @Override
    public void eliminar(Long id) {
        productoRepository.deleteById(id);
    }

    @Override
    public Producto buscar(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    @Override
    public List<Producto> listar() {
        List<Producto> productos = new ArrayList<>();
        productoRepository.findAll().forEach(productos::add);
        return productos;
    }
}

package com.tecsup.demo.services;

import com.tecsup.demo.domain.entities.Producto;
import java.util.List;

public interface ProductoService {
    public Producto grabar(Producto producto);
    public void eliminar(Long id);
    public Producto buscar(Long id);
    public List<Producto> listar();
}
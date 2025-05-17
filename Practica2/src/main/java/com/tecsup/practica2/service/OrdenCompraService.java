package com.tecsup.practica2.service;

import com.tecsup.practica2.model.OrdenCompra;

import java.util.List;

public interface OrdenCompraService {
    List<OrdenCompra> listar();
    OrdenCompra buscarPorId(Long id);
    void guardar(OrdenCompra ordenCompra);
    void actualizar(OrdenCompra ordenCompra);
    void eliminar(Long id);
}

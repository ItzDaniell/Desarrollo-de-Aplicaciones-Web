package com.tecsup.practica2.service;

import com.tecsup.practica2.dao.OrdenCompraDAO;
import com.tecsup.practica2.model.OrdenCompra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdenCompraServiceImpl implements OrdenCompraService {

    private final OrdenCompraDAO ordenCompraDAO;

    @Autowired
    public OrdenCompraServiceImpl(OrdenCompraDAO ordenCompraDAO) {
        this.ordenCompraDAO = ordenCompraDAO;
    }

    @Override
    public List<OrdenCompra> listar() {
        return ordenCompraDAO.listar();
    }

    @Override
    public OrdenCompra buscarPorId(Long id) {
        return ordenCompraDAO.buscarPorId(id);
    }

    @Override
    public void guardar(OrdenCompra ordenCompra) {
        ordenCompraDAO.guardar(ordenCompra);
    }

    @Override
    public void actualizar(OrdenCompra ordenCompra) {
        ordenCompraDAO.actualizar(ordenCompra);
    }

    @Override
    public void eliminar(Long id) {
        ordenCompraDAO.eliminar(id);
    }
}

package com.tecsup.practica2.dao;

import com.tecsup.practica2.model.OrdenCompra;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class OrdenCompraDAOImpl implements OrdenCompraDAO {
    private final Map<Long, OrdenCompra> baseDatos = new HashMap<>();

    @Override
    public List<OrdenCompra> listar() {
        return new ArrayList<>(baseDatos.values());
    }

    @Override
    public OrdenCompra buscarPorId(Long id) {
        return baseDatos.get(id);
    }

    @Override
    public void guardar(OrdenCompra ordenCompra) {
        baseDatos.put(ordenCompra.getNroOrdenC(), ordenCompra);
    }

    @Override
    public void actualizar(OrdenCompra ordenCompra) {
        baseDatos.put(ordenCompra.getNroOrdenC(), ordenCompra);
    }

    @Override
    public void eliminar(Long id) {
        baseDatos.remove(id);
    }
}

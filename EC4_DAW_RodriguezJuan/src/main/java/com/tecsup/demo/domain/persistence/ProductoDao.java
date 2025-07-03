package com.tecsup.demo.domain.persistence;

import com.tecsup.demo.domain.entities.Producto;
import org.springframework.data.repository.CrudRepository;

public interface ProductoDao extends CrudRepository<Producto, Long> {
}
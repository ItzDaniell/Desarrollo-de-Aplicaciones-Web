package com.tecsup.demo.repositories;

import com.tecsup.demo.domain.entities.Producto;
import org.springframework.data.repository.CrudRepository;

public interface ProductoRepository extends CrudRepository<Producto, Long> {
}

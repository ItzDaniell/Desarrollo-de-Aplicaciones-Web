package com.tecsup.laboratorio13.repositories;

import com.tecsup.laboratorio13.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}

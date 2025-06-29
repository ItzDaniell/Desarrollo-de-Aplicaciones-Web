package com.tecsup.demo.repositories;

import com.tecsup.demo.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
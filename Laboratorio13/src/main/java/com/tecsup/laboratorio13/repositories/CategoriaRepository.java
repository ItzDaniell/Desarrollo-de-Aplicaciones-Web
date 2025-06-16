package com.tecsup.laboratorio13.repositories;

import com.tecsup.laboratorio13.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}

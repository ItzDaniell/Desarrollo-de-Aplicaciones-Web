package com.tecsup.demo.domain.persistence;

import com.tecsup.demo.domain.entities.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;

public interface IUsuarioDao extends CrudRepository<Usuario, Long> {
    public Usuario findByUsername(String username);
}

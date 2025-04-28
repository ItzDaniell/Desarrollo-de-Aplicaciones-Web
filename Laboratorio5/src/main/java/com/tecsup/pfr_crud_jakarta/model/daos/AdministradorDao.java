package com.tecsup.pfr_crud_jakarta.model.daos;

import com.tecsup.pfr_crud_jakarta.model.entities.Administrador;

public interface AdministradorDao {
    Administrador validar(String user, String password);
}

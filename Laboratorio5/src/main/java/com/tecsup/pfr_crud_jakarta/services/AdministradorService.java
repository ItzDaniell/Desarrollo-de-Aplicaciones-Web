package com.tecsup.pfr_crud_jakarta.services;

import com.tecsup.pfr_crud_jakarta.model.entities.Administrador;

public interface AdministradorService {
    public Administrador validar(String u, String p);
}

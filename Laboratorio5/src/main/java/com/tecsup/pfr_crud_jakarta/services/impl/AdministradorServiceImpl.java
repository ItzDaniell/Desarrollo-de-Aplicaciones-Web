package com.tecsup.pfr_crud_jakarta.services.impl;
import com.tecsup.pfr_crud_jakarta.model.daos.AdministradorDao;
import com.tecsup.pfr_crud_jakarta.model.daos.impl.DaoFactory;
import com.tecsup.pfr_crud_jakarta.model.entities.Administrador;
import com.tecsup.pfr_crud_jakarta.services.AdministradorService;
import com.tecsup.pfr_crud_jakarta.util.Util;

public class AdministradorServiceImpl implements AdministradorService {

    private AdministradorDao dao;

    public AdministradorServiceImpl() {
        dao = DaoFactory.getAdministradorDao(Util.opc);
    }

    @Override
    public Administrador validar(String u, String p) {
        return dao.validar(u, p);
    }
}


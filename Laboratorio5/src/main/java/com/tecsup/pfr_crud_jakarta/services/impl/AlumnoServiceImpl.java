package com.tecsup.pfr_crud_jakarta.services.impl;

import com.tecsup.pfr_crud_jakarta.model.daos.AlumnoDao;
import com.tecsup.pfr_crud_jakarta.model.daos.impl.DaoFactory;
import com.tecsup.pfr_crud_jakarta.model.entities.Alumno;
import com.tecsup.pfr_crud_jakarta.services.AlumnoService;
import com.tecsup.pfr_crud_jakarta.util.Util;

import java.util.List;

public class AlumnoServiceImpl implements AlumnoService {
    private AlumnoDao dao;
    public AlumnoServiceImpl() {
        dao = DaoFactory.getAlumnoDao(Util.opc);
    }

    @Override
    public void grabar(Alumno alumno) {
        dao.create(alumno);
    }

    @Override
    public Alumno buscar(String id) {
        return dao.find(id);
    }

    @Override
    public List<Alumno> listar() {
        return dao.findAll();
    }

    @Override
    public void actualizar(Alumno curso) {
        dao.update(curso);
    }

    @Override
    public void borrar(String id) {
        dao.delete(id);
    }
}

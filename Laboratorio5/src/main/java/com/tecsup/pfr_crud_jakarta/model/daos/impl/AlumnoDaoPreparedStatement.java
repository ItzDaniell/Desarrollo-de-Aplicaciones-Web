package com.tecsup.pfr_crud_jakarta.model.daos.impl;

import com.tecsup.pfr_crud_jakarta.model.daos.AlumnoDao;
import com.tecsup.pfr_crud_jakarta.model.entities.Alumno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class AlumnoDaoPreparedStatement implements AlumnoDao {
    private Connection con;
    private ResultSet rs;
    private PreparedStatement pst;

    @Override
    public List<Alumno> findByNombre(String nombre) {
        return List.of();
    }

    @Override
    public void create(Alumno alumno) {
    }

    @Override
    public Alumno find(String id) {
        return null;
    }

    @Override
    public List<Alumno> findAll() {
        return List.of();
    }

    @Override
    public void update(Alumno alumno) {
    }

    @Override
    public void delete(String id) {

    }
}

package com.tecsup.pfr_crud_jakarta.model.daos.impl;

import com.tecsup.pfr_crud_jakarta.model.daos.CursoDao;
import com.tecsup.pfr_crud_jakarta.model.entities.Curso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class CursoDaoPreparedStatement implements CursoDao {

    private Connection con;
    private ResultSet rs;
    private PreparedStatement pst;

    @Override
    public List<Curso> findByRangeCreditos(int min, int max) {
        return List.of();
    }

    @Override
    public List<Curso> findByNombre(String nombre) {
        return List.of();
    }

    @Override
    public void create(Curso curso) {
    }

    @Override
    public Curso find(String id) {
        return null;
    }

    @Override
    public List<Curso> findAll() {
        return List.of();
    }

    @Override
    public void update(Curso curso) {
    }

    @Override
    public void delete(String id) {
    }
}
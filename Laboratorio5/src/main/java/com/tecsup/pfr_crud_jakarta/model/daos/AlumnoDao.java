package com.tecsup.pfr_crud_jakarta.model.daos;

import com.tecsup.pfr_crud_jakarta.model.entities.Alumno;

import java.util.List;

public interface AlumnoDao extends EntidadDao<Alumno,String> {
    public List<Alumno> findByNombre(String nombre);
}

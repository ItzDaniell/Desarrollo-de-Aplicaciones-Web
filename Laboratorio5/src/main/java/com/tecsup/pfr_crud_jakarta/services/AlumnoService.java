package com.tecsup.pfr_crud_jakarta.services;

import com.tecsup.pfr_crud_jakarta.model.entities.Alumno;

import java.util.List;

public interface AlumnoService {
    public void grabar(Alumno alumno);
    public Alumno buscar(String id);
    public List<Alumno> listar();
    public void actualizar(Alumno alumno);
    public void borrar(String id);
}

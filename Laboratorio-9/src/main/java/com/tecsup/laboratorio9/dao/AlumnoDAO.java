package com.tecsup.laboratorio9.dao;

import com.tecsup.laboratorio9.model.Alumno;

import java.util.List;

public interface AlumnoDAO {
    List<Alumno> listar();
    Alumno buscarPorId(int id);
    void guardar(Alumno alumno);
    void actualizar(Alumno alumno);
    void eliminar(int id);
}

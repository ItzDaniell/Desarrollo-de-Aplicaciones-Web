package com.tecsup.laboratorio9.service;

import com.tecsup.laboratorio9.model.Alumno;

import java.util.List;

public interface AlumnoService {
    List<Alumno> listar();
    Alumno buscarPorId(int id);
    void guardar(Alumno alumno);
    void actualizar(Alumno alumno);
    void eliminar(int id);
}

package com.tecsup.demospring1.dao;

import com.tecsup.demospring1.model.Curso;

import java.util.List;

public interface CursoDAO {
    List<Curso> listar();
    Curso buscarPorId(Long id);
    void guardar(Curso curso);
    void actualizar(Curso curso);
    void eliminar(Long id);
}

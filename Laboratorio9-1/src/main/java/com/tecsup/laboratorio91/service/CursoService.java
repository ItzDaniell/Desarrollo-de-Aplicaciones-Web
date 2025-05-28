package com.tecsup.laboratorio91.service;

import com.tecsup.laboratorio91.model.entity.Curso;
import java.util.List;

public interface CursoService {
    public List<Curso> listar();

    public void grabar(Curso curso);

    public Curso buscar(Integer id);

    public void eliminar(Integer id);
}

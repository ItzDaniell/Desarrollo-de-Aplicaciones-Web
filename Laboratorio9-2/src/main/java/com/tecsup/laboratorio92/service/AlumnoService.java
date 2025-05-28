package com.tecsup.laboratorio92.service;

import com.tecsup.laboratorio92.model.entities.Alumno;
import java.util.List;

public interface AlumnoService {
    public List<Alumno> listar();

    public void grabar(Alumno alumno);

    public Alumno buscar(Integer id);

    public void eliminar(Integer id);
}

package com.tecsup.laboratorio9.service;

import com.tecsup.laboratorio9.dao.AlumnoDAO;
import com.tecsup.laboratorio9.model.Alumno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlumnoServiceImpl implements AlumnoService {

    private final AlumnoDAO alumnoDAO;

    @Autowired
    public AlumnoServiceImpl(AlumnoDAO alumnoDAO) {
        this.alumnoDAO = alumnoDAO;
    }

    @Override
    public List<Alumno> listar() {
        return alumnoDAO.listar();
    }

    @Override
    public Alumno buscarPorId(int id) {
        return alumnoDAO.buscarPorId(id);
    }

    @Override
    public void guardar(Alumno alumno) {
        alumnoDAO.guardar(alumno);
    }

    @Override
    public void actualizar(Alumno alumno) {
        alumnoDAO.actualizar(alumno);
    }

    @Override
    public void eliminar(int id) {
        alumnoDAO.eliminar(id);
    }
}
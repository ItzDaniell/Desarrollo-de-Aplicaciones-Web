package com.tecsup.demo.service;

import com.tecsup.demo.model.dao.AlumnoRepository;
import com.tecsup.demo.model.documents.Alumno;
import com.tecsup.demo.model.documents.Curso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AlumnoServiceImpl implements AlumnoService {

    @Autowired
    private AlumnoRepository dao;

    @Override
    @Transactional(readOnly = true)
    public List<Alumno> listar() {
        return (List<Alumno>)dao.findAll();
    }

    @Override
    public void grabar(Alumno alumno) {
        dao.save(alumno);
    }

    @Override
    @Transactional(readOnly = true)
    public Alumno buscar(String id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    public void eliminar(String id) {
        dao.deleteById(id);
    }
}

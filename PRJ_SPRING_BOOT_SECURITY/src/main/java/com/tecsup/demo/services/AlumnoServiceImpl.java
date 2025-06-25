package com.tecsup.demo.services;

import com.tecsup.demo.domain.entities.Alumno;
import com.tecsup.demo.domain.persistence.AlumnoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AlumnoServiceImpl implements AlumnoService{
    @Autowired
    public AlumnoDao alumnoDao;

    @Override
    @Transactional
    public void grabar(Alumno alumno) {
        alumnoDao.save(alumno);
    }

    @Override
    @Transactional
    public void eliminar(int id) {
        alumnoDao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Alumno buscar(Integer id) {
        return alumnoDao.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Alumno> listar() {
        return (List<Alumno>) alumnoDao.findAll();
    }
}

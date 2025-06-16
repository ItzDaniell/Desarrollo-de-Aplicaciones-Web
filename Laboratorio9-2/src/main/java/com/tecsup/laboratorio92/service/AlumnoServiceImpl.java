package com.tecsup.laboratorio92.service;

import com.tecsup.laboratorio92.model.daos.AlumnoRepository;
import com.tecsup.laboratorio92.model.entities.Alumno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class AlumnoServiceImpl implements AlumnoService {
    @Autowired
    private AlumnoRepository alumnoRepository;

    @Override
    @Transactional(readOnly = false)
    public void grabar(Alumno alumno) {
        alumnoRepository.save(alumno);
    }

    @Override
    @Transactional(readOnly = false)
    public void eliminar(Integer id) {
        alumnoRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Alumno buscar(Integer id) {
        return alumnoRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Alumno> listar() {
        return (List<Alumno>)alumnoRepository.findAll();
    }

    @Transactional
    public void eliminarAlumno(int id) {
        Alumno alumno = alumnoRepository.findById(id).orElseThrow();
        alumnoRepository.delete(alumno); // Estado: Removed
        // El alumno será eliminado al hacer commit
    }
}

package com.tecsup.pfr_crud_jakarta.model.daos.impl;

import com.tecsup.pfr_crud_jakarta.model.daos.AlumnoDao;
import com.tecsup.pfr_crud_jakarta.model.entities.Alumno;

import java.util.ArrayList;
import java.util.List;

public class AlumnoDaoMemory implements AlumnoDao {
    private List<Alumno> listadeAlumnos;

    public AlumnoDaoMemory() {
        listadeAlumnos = new ArrayList<>();
        /*listaDeCursos = List.of(
            new Curso("100", "Programmer", 3),
            new Curso("200", "Developer", 4),
            new Curso("300", "Expert", 5)
        );*/
    }

    @Override
    public void create(Alumno alumno) {
        listadeAlumnos.add(alumno);
    }

    @Override
    public Alumno find(String id) {

        for (Alumno alumno : listadeAlumnos) {
            if (alumno.getCodigo().equals(id)) {
                return alumno;
            }
        }

        return null;
    }

    @Override
    public List<Alumno> findAll() {
        return listadeAlumnos;
    }

    @Override
    public void update(Alumno alumno) {
        for (Alumno a : listadeAlumnos) {
            if (a.getCodigo().equals(alumno.getCodigo())) {
                a.setNombre(alumno.getNombre());
                a.setApellido(alumno.getApellido());
                a.setFecha_nacimiento(alumno.getFecha_nacimiento());
                a.setSexo(alumno.getSexo());
            }
        }
    }

    @Override
    public void delete(String id) {
        listadeAlumnos.removeIf(alumno -> alumno.getCodigo().equals(id));
    }

    @Override
    public List<Alumno> findByNombre(String nombre) {
        return List.of();
    }
}

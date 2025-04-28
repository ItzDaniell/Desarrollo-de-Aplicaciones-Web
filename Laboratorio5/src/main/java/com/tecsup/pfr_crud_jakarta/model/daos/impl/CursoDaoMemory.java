package com.tecsup.pfr_crud_jakarta.model.daos.impl;

import com.tecsup.pfr_crud_jakarta.model.daos.CursoDao;
import com.tecsup.pfr_crud_jakarta.model.entities.Curso;
import java.util.ArrayList;
import java.util.List;

public class CursoDaoMemory implements CursoDao {
    private List<Curso> listaDeCursos;

    public CursoDaoMemory() {
        listaDeCursos = new ArrayList<>();
        /*listaDeCursos = List.of(
            new Curso("100", "Programmer", 3),
            new Curso("200", "Developer", 4),
            new Curso("300", "Expert", 5)
        );*/
    }

    @Override
    public void create(Curso curso) {
        listaDeCursos.add(curso);
    }

    @Override
    public Curso find(String id) {

        for (Curso curso : listaDeCursos) {
            if (curso.getCodigo().equals(id)) {
                return curso;
            }
        }

        return null;
    }

    @Override
    public List<Curso> findAll() {
        return listaDeCursos;
    }

    @Override
    public void update(Curso curso) {
        for (Curso c : listaDeCursos) {
            if (c.getCodigo().equals(curso.getCodigo())) {
                c.setNombre(curso.getNombre());
                c.setCreditos(curso.getCreditos());
            }
        }
    }

    @Override
    public void delete(String id) {
        listaDeCursos.removeIf(curso -> curso.getCodigo().equals(id));
    }

    @Override
    public List<Curso> findByRangeCreditos(int min, int max) {
        List<Curso> cursos = new ArrayList<>();
        listaDeCursos.forEach(curso -> {
            if (curso.getCreditos() >= min && curso.getCreditos() <= max) {
                cursos.add(curso);
            }
        });
        return cursos;
    }

    @Override
    public List<Curso> findByNombre(String nombre) {
        List<Curso> cursos = new ArrayList<>();
        listaDeCursos.forEach(curso -> {
            if (curso.getNombre().contains(nombre)) {
                cursos.add(curso);
            }
        });
        return cursos;
    }
}

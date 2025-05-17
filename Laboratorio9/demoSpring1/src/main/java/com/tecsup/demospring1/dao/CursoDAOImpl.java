package com.tecsup.demospring1.dao;

import com.tecsup.demospring1.dao.CursoDAO;
import com.tecsup.demospring1.model.Curso;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CursoDAOImpl implements CursoDAO {

    private final Map<Long, Curso> baseDatos=new HashMap<>();
    private long idActual=1;

    @Override
    public List<Curso> listar() {
        return new ArrayList<>(baseDatos.values());
    }

    @Override
    public Curso buscarPorId(Long id) {
        return baseDatos.get(id);
    }

    @Override
    public void guardar(Curso curso) {
        curso.setId(idActual++);
        baseDatos.put(curso.getId(), curso);
    }

    @Override
    public void actualizar(Curso curso) {
        baseDatos.put(curso.getId(), curso);
    }

    @Override
    public void eliminar(Long id) {
        baseDatos.remove(id);
    }
}

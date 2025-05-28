package com.tecsup.laboratorio91.service;

import com.tecsup.laboratorio91.model.daos.CursoRepository;
import com.tecsup.laboratorio91.model.entity.Curso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CursoServiceImpl implements CursoService {

    @Autowired
    private CursoRepository dao;

    @Override
    @Transactional(readOnly = true)
    public List<Curso> listar() {
        return (List<Curso>) dao.findAll();
    }

    @Override
    @Transactional(readOnly = false)
    public void grabar(Curso curso) {
        dao.save(curso);
    }

    @Override
    @Transactional(readOnly = true)
    public Curso buscar(Integer id) {
        return  dao.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Integer id) {
        dao.deleteById(id);
    }
}

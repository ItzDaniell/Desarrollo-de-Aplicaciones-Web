package com.tecsup.practica2.service;

import com.tecsup.practica2.dao.LaboratorioDAO;
import com.tecsup.practica2.model.Laboratorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LaboratorioServiceImpl implements LaboratorioService {
    private final LaboratorioDAO laboratorioDAO;

    @Autowired
    public LaboratorioServiceImpl(LaboratorioDAO laboratorioDAO) {
        this.laboratorioDAO = laboratorioDAO;
    }

    @Override
    public List<Laboratorio> listar() {
        return laboratorioDAO.listar();
    }

    @Override
    public Laboratorio buscarPorId(Long id) {
        return laboratorioDAO.buscarPorId(id);
    }

    @Override
    public void guardar(Laboratorio laboratorio) {
        laboratorioDAO.guardar(laboratorio);
    }

    @Override
    public void actualizar(Laboratorio laboratorio) {
        laboratorioDAO.actualizar(laboratorio);
    }

    @Override
    public void eliminar(Long id) {
        laboratorioDAO.eliminar(id);
    }
}

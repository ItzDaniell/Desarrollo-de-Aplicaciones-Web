package com.tecsup.practica2.dao;

import com.tecsup.practica2.model.Laboratorio;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class LaboratorioDAOImpl implements LaboratorioDAO {
    private final Map<Long, Laboratorio> baseDatos = new HashMap<>();

    @Override
    public List<Laboratorio> listar() {
        return new ArrayList<>(baseDatos.values());
    }

    @Override
    public Laboratorio buscarPorId(Long codLab) {
        return baseDatos.get(codLab);
    }

    @Override
    public void guardar(Laboratorio laboratorio) {
        baseDatos.put(laboratorio.getCodLab(), laboratorio);
    }

    @Override
    public void actualizar(Laboratorio laboratorio) {
        baseDatos.put(laboratorio.getCodLab(), laboratorio);
    }

    @Override
    public void eliminar(Long codLab) {
        baseDatos.remove(codLab);
    }
}
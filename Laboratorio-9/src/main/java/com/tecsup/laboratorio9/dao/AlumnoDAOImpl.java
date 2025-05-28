package com.tecsup.laboratorio9.dao;

import com.tecsup.laboratorio9.model.Alumno;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AlumnoDAOImpl implements AlumnoDAO {

    private final Map<Integer, Alumno> baseDatos = new HashMap<>();

    @Override
    public List<Alumno> listar() {
        return new ArrayList<>(baseDatos.values());
    }

    @Override
    public Alumno buscarPorId(int id) {
        return baseDatos.get(id);
    }

    @Override
    public void guardar(Alumno alumno) {
        baseDatos.put(alumno.getCodigo(), alumno);
    }

    @Override
    public void actualizar(Alumno alumno) {
        baseDatos.put(alumno.getCodigo(), alumno);
    }

    @Override
    public void eliminar(int id) {
        baseDatos.remove(id);
    }
}

package com.tecsup.practica2.dao;

import com.tecsup.practica2.model.Laboratorio;

import java.util.List;

public interface LaboratorioDAO {
    List<Laboratorio> listar();
    Laboratorio buscarPorId(Long id);
    void guardar(Laboratorio laboratorio);
    void actualizar(Laboratorio laboratorio);
    void eliminar(Long id);
}

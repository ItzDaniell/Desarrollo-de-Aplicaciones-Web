package com.tecsup.practica2.service;

import com.tecsup.practica2.model.Laboratorio;

import java.util.List;

public interface LaboratorioService {
    List<Laboratorio> listar();
    Laboratorio buscarPorId(Long id);
    void guardar(Laboratorio laboratorio);
    void actualizar(Laboratorio laboratorio);
    void eliminar(Long id);
}

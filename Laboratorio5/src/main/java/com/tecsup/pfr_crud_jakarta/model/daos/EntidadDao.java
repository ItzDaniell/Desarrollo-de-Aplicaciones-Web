package com.tecsup.pfr_crud_jakarta.model.daos;

import java.util.List;

public interface EntidadDao<T,V> {
    public void create(T t);
    public T find(V id);
    public List<T> findAll();
    public void update(T t);
    public void delete(V id);
}



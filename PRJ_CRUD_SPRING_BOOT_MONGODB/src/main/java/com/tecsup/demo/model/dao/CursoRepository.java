package com.tecsup.demo.model.dao;

import com.tecsup.demo.model.documents.Curso;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CursoRepository extends MongoRepository<Curso, String> {
}


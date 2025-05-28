package com.tecsup.demo.model.dao;

import com.tecsup.demo.model.documents.Alumno;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AlumnoRepository extends MongoRepository<Alumno, String> {
}

package com.tecsup.demo.domain.persistence;

import com.tecsup.demo.domain.entities.Auditoria;
import org.springframework.data.repository.CrudRepository;

public interface AuditoríaDao extends CrudRepository<Auditoria, Integer> {
}

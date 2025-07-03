package com.tecsup.demo.repositories;

import com.tecsup.demo.domain.entities.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
}

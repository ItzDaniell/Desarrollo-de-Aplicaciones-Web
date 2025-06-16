package com.tecsup.laboratorio13.repositories;

import com.tecsup.laboratorio13.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.nio.channels.FileChannel;
import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {
}

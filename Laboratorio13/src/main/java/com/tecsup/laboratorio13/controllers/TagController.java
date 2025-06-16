package com.tecsup.laboratorio13.controllers;

import com.tecsup.laboratorio13.entities.Tag;
import com.tecsup.laboratorio13.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tag")
public class TagController {

    @Autowired
    private TagRepository tagRepository;

    @PostMapping
    public Tag create(@RequestBody Tag tag) {
        return tagRepository.save(tag);
    }

    @GetMapping
    public List<Tag> findAll() {
        return tagRepository.findAll();
    }

    @GetMapping("/{id}")
    public Tag findById(@PathVariable Long id) {
        return tagRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id, @RequestBody Tag tag) {
        Optional<Tag> tagOptional = tagRepository.findById(id);
        if (tagOptional.isPresent()) {
            Tag t = tagOptional.get();
            t.setNombre(tag.getNombre());
            tagRepository.save(t);
            return "Tag Actualizado";
        } else {
            return "Tag no encontrado";
        }
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        Optional<Tag> tagOptional = tagRepository.findById(id);
        if (tagOptional.isPresent()) {
            tagRepository.deleteById(id);
            return "Tag eliminado";
        } else {
            return "Tag no encontrada";
        }
    }
}

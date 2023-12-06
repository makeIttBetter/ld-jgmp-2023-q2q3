package com.develop.springboot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * CrudController interface provides CRUD operations for entities.
 * @param <T> entity type
 * @param <ID> entity ID type
 */
public interface CrudController<T, ID> {

    @PostMapping
    ResponseEntity<T> create(@RequestBody T entity);

    @GetMapping("/{id}")
    ResponseEntity<T> getById(@PathVariable ID id);

    @GetMapping
    ResponseEntity<List<T>> getAll();

    @PutMapping("/{id}")
    ResponseEntity<T> update(@PathVariable ID id, @RequestBody T updatedEntity);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable ID id);

}

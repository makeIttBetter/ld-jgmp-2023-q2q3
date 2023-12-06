package com.develop.springmvc.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

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

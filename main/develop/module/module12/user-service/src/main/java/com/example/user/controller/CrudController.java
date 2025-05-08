package com.example.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * CrudController interface provides CRUD operations for entities.
 *
 * @param <T>  entity type
 * @param <ID> entity ID type
 */
public interface CrudController<T, ID> {

    @Operation(summary = "Create a new entity", description = "Creates a new entity of type T")
    @PostMapping
    ResponseEntity<T> create(@RequestBody T entity);

    @Operation(summary = "Get entity by ID", description = "Retrieves an entity of type T by its ID")
    @GetMapping("/{id}")
    ResponseEntity<T> getById(@Parameter(description = "ID of the entity to retrieve", required = true) @PathVariable ID id);

    @Operation(summary = "Get all entities", description = "Retrieves all entities of type T")
    @GetMapping
    ResponseEntity<List<T>> getAll();

    @Operation(summary = "Update an entity by ID", description = "Updates an existing entity of type T by its ID")
    @PutMapping("/{id}")
    ResponseEntity<T> update(@Parameter(description = "ID of the entity to update", required = true) @PathVariable ID id, @RequestBody T updatedEntity);

    @Operation(summary = "Delete an entity by ID", description = "Deletes an existing entity of type T by its ID")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@Parameter(description = "ID of the entity to delete", required = true) @PathVariable ID id);
}

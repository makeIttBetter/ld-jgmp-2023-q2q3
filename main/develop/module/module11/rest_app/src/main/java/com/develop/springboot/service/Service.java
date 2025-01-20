package com.develop.springboot.service;

import java.util.List;
import java.util.Optional;

/**
 * Service interface is a service for customizing service.
 * It provides methods for basic CRUD operations.
 * <p> It will be used for saving, finding and deleting entities.
 */
public interface Service<Entity, ID> {
    void save(Entity entity);

    Optional<Entity> findById(ID id);

    List<Entity> findAll();

    void deleteById(ID id);
}

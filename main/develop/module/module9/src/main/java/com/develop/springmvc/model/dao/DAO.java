package com.develop.springmvc.model.dao;


import java.util.List;
import java.util.Optional;

public interface DAO<Entity, ID> {

    /**
     * Create/Update the given entity.
     *
     * @param entity the entity to save
     */
    void save(Entity entity);

    /**
     * Retrieves an entity by its ID.
     *
     * @param id the ID of the entity to retrieve
     * @return the entity with the given ID or Optional.empty() if none found
     */
    Optional<Entity> findById(ID id);

    /**
     * Retrieves all entities.
     *
     * @return a list of entities
     */
    List<Entity> findAll();

    /**
     * Deletes an entity by its ID.
     *
     * @param id the ID of the entity to delete
     */
    void deleteById(ID id);
}



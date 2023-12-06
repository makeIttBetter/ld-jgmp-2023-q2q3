package com.develop.springmvc.service;

import java.util.List;
import java.util.Optional;

public interface Service<Entity, ID> {
    void save(Entity entity);

    Optional<Entity> findById(ID id);

    List<Entity> findAll();

    void deleteById(ID id);
}

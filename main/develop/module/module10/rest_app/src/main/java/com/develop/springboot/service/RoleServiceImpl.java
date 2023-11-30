package com.develop.springboot.service;

import com.develop.springboot.model.entities.Role;
import com.develop.springboot.model.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * RoleServiceImpl class is a service for customizing role service.
 * It provides methods for saving, finding and deleting roles.
 * <p> It implements Service interface, which means it will be used for saving, finding and deleting roles.
 */
@Slf4j
@org.springframework.stereotype.Service
public class RoleServiceImpl implements Service<Role, UUID> { // Assuming you have a generic Service interface.

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void save(Role role) {
        log.info("Attempting to save role: {}", role);
        roleRepository.save(role);
        log.info("Role saved successfully: {}", role);
    }

    @Override
    public Optional<Role> findById(UUID id) {
        log.info("Searching for role by id: {}", id);
        Optional<Role> role = roleRepository.findById(id);
        log.info("Role search result for id {}: {}", id, role);
        return role;
    }

    public Optional<Role> findByConstantCode(String constantCode) {
        log.info("Searching for role by constantCode: {}", constantCode);
        Optional<Role> role = roleRepository.findByConstantCode(constantCode);
        log.info("Role search result for constantCode {}: {}", constantCode, role);
        return role;
    }

    @Override
    public List<Role> findAll() {
        log.info("Retrieving all roles");
        List<Role> roles = roleRepository.findAll();
        log.info("Found {} roles", roles.size());
        return roles;
    }

    @Override
    public void deleteById(UUID id) {
        log.info("Deleting role by id: {}", id);
        roleRepository.deleteById(id);
        log.info("Role with id {} deleted successfully", id);
    }
}

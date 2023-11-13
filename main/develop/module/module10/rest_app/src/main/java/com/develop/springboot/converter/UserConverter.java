package com.develop.springboot.converter;

import com.develop.springboot.dto.UserDTO;
import com.develop.springboot.model.entities.Role;
import com.develop.springboot.model.entities.User;
import com.develop.springboot.service.RoleServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
public class UserConverter {

    @Autowired
    private RoleServiceImpl roleService;

    public UserDTO convertToUserDTO(User user) {
        log.debug("Converting User entity to UserDTO: {}", user);
        UserDTO dto = new UserDTO();
        dto.setId(user.getId()); // Copy the id
        dto.setUsername(user.getUsername()); // Copy the username
        dto.setName(user.getName()); // Copy the name
        dto.setEmail(user.getEmail()); // Copy the email

        // Convert the set of Role entities to a set of String representing the constantCode
        dto.setRoles(user.getRoles().stream()
                .map(Role::getConstantCode)
                .collect(Collectors.toSet()));

        return dto;
    }

    public User convertToUserEntity(UserDTO dto) {
        log.debug("Converting UserDTO to User entity: {}", dto);
        User user = new User();
        user.setId(dto.getId()); // assuming there's a setter
        user.setUsername(dto.getUsername());
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        // Don't set the password directly! You should use an encoder in the actual user creation process.

        // Convert roles
        Set<Role> userRoles = new HashSet<>();
        if (dto.getRoles() != null) {
            for (String roleCode : dto.getRoles()) {
                roleService.findByConstantCode(roleCode).ifPresent(userRoles::add);
            }
        }
        user.setRoles(userRoles);

        return user;
    }
}

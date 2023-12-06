package com.develop.springmvc.converter;

import com.develop.springmvc.dto.UserDTO;
import com.develop.springmvc.model.entities.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserConverter {

    public UserDTO convertToUserDTO(User user) {
        log.debug("Converting User entity to UserDTO: {}", user);
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        return dto;
    }

    public User convertToUserEntity(UserDTO dto) {
        log.debug("Converting UserDTO to User entity: {}", dto);
        User user = new User();
        user.setId(dto.getId());
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        return user;
    }
}

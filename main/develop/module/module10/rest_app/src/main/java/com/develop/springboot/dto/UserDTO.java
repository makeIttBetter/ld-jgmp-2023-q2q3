package com.develop.springboot.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;
import java.util.UUID;

/**
 * DTO for user.
 *
 * @see com.develop.springboot.controller.api.ApiUserController
 */
@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class UserDTO {
    private UUID id;
    @EqualsAndHashCode.Include
    private String username;
    private String name;
    private String email;
    private Set<String> roles; // Representing roles by their constantCode
}

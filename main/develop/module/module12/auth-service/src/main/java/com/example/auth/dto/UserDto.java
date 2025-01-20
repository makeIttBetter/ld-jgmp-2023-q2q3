package com.example.auth.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserDto {
    private String id;
    @EqualsAndHashCode.Include
    private String username;
    @EqualsAndHashCode.Include
    private String email;
    private String password;
    private Set<RoleDto> roles;
}

package com.example.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Role data transfer object.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {
    private String id;
    private String constantCode;
}

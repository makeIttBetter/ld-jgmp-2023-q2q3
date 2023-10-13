package com.develop.springmvc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class UserDTO {
    private UUID id;
    private String name;
    @EqualsAndHashCode.Include
    private String email;
    private List<UUID> tickets;
}

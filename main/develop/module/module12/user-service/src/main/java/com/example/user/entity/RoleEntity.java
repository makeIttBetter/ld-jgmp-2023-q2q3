package com.example.user.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serial;

/**
 * Stores roles like ROLE_USER, ROLE_ADMIN, etc.
 */
@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class RoleEntity extends Model {

    @Serial
    private static final long serialVersionUID = 2658240715842215095L;

    /**
     * e.g. "USER", "ADMIN", etc.
     */
    @EqualsAndHashCode.Include
    private String constantCode;

}

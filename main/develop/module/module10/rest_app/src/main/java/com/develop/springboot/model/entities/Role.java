package com.develop.springboot.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serial;

@Entity
@Table(name = "role")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Role extends Model {
    @Serial
    private static final long serialVersionUID = 2658240715842215095L;

    private String constantCode; //ROLE_USER, ROLE_ADMIN, etc.

}

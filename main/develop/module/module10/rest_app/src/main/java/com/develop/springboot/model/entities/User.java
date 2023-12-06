package com.develop.springboot.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.util.Set;

@Entity
@Table(name = "\"user\"")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class User extends Model {
    @Serial
    private static final long serialVersionUID = 505215941527106892L;

    @Column(nullable = false, unique = true)
    @EqualsAndHashCode.Include
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    @OneToMany(
            targetEntity = Ticket.class,
            mappedBy = "user", cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private Set<Ticket> tickets;
}


package com.develop.springmvc.model.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.util.List;

@Entity
@Table(name = "\"user\"")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class User extends Model {
    @Serial
    private static final long serialVersionUID = 505215941527106892L;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    @EqualsAndHashCode.Include
    private String email;

    //    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @OneToMany(targetEntity = Ticket.class,
            mappedBy = "user", cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private List<Ticket> tickets;
}


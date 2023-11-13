package com.develop.springboot.model.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.time.LocalDateTime;

@Entity
@Table(name = "event")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class Event extends Model {
    @Serial
    private static final long serialVersionUID = -536968910318363157L;

    @Column(nullable = false, unique = true)
    @EqualsAndHashCode.Include
    private String title;

    @Column(nullable = false, unique = true)
    @EqualsAndHashCode.Include
    private LocalDateTime date;


}

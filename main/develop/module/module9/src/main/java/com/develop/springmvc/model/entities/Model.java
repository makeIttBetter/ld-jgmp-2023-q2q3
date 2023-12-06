package com.develop.springmvc.model.entities;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
@EqualsAndHashCode
public abstract class Model implements Serializable {
    @Serial
    private static final long serialVersionUID = 7945147474269998569L;

    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    private UUID id;

    // other fields, getters, and setters


    public Model() {
    }

    public Model(UUID id) {
        this.id = id;
    }
}

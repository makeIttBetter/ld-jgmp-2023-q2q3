package org.example.model.entities;

import jakarta.persistence.*;
import lombok.Getter;


@Getter
@Entity
@Table(name = "student_address")
public class StudentAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;

    @Column(name = "student_id")
    private final Long studentId;

    @Column(name = "address")
    private final String address;

    public StudentAddress(Long id, Long studentId, String address) {
        this.id = id;
        this.studentId = studentId;
        this.address = address;
    }
}


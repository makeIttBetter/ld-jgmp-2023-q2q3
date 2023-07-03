package org.example.model.repository;

import org.example.model.entities.StudentAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentAddressRepository extends JpaRepository<StudentAddress, Long> {
}

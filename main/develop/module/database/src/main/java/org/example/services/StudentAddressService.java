package org.example.services;

import org.example.model.entities.StudentAddress;
import org.example.model.repository.StudentAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentAddressService {

    @Autowired
    private StudentAddressRepository studentAddressRepository;

    public StudentAddress save(StudentAddress studentAddress) {
        return studentAddressRepository.save(studentAddress);
    }
}

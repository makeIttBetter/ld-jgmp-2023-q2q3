package org.example.services;

import org.example.model.entities.Subject;
import org.example.model.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;


    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public Subject create(Subject subject) {
        System.out.println(Thread.currentThread().getName() + " - Create operation started.");
        return subjectRepository.save(subject);
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public void countAll() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " - Count operation started.");
        System.out.println(Thread.currentThread().getName() + " - Current count: " + subjectRepository.count());

        Thread.sleep(3000);

        System.out.println(Thread.currentThread().getName() + " - Updated count: " + subjectRepository.count());
    }
}
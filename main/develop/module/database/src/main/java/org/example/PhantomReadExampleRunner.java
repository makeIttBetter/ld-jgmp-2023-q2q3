package org.example;

import org.example.model.entities.Subject;
import org.example.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class PhantomReadExampleRunner implements CommandLineRunner {

    @Autowired
    private SubjectService subjectService;

    @Override
    public void run(String... args) throws Exception {
        Thread thread1 = new Thread(() -> {
            try {
                subjectService.countAll();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread thread2 = new Thread(() -> {
            Subject subject = new Subject();
            subject.setSubjectName("Subject test name");
            subject.setTutor("Subject test tutor");
            subjectService.create(subject);
        });

        thread1.start();
        Thread.sleep(500);
        thread2.start();
    }
}

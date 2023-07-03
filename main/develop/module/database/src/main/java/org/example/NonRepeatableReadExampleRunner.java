package org.example;

import org.example.model.entities.ExamResult;
import org.example.services.ExamResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class NonRepeatableReadExampleRunner implements CommandLineRunner {
    @Autowired
    private ExamResultService examResultService;

    @Override
    public void run(String... args) throws Exception {
        int studentId = 1;
        int oldMark = 1;
        int newMark = 2;

        ExamResult result = examResultService.read(studentId);
        System.out.println("Current mark: " + result.getMark());
        examResultService.update(studentId, oldMark);
        result = examResultService.read(studentId);
        System.out.println("Current mark: " + result.getMark());

        Thread thread3 = new Thread(() -> {
            try {
                examResultService.getExamResult(studentId);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread thread4 = new Thread(() -> {
            examResultService.updateExamResult(studentId, newMark);
        });

        thread3.start();
        Thread.sleep(500);
        thread4.start();
    }
}

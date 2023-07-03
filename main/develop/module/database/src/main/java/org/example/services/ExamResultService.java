package org.example.services;

import org.example.model.entities.ExamResult;
import org.example.model.repository.ExamResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ExamResultService {

    @Autowired
    private ExamResultRepository examResultRepository;

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public ExamResult updateExamResult(int studentId, int newMark) {
        System.out.println(Thread.currentThread().getName() + " - Update operation started.");
        ExamResult examResult = examResultRepository.findById(studentId).get();
        examResult.setMark(newMark);
        System.out.println(Thread.currentThread().getName() + " - New mark for exam was set: " + newMark);
        ExamResult result = examResultRepository.save(examResult);
        ExamResult examResult1 = examResultRepository.findById(studentId).get();
        System.out.println(Thread.currentThread().getName() + " - New mark for exam was set (read): " + examResult1.getMark());
        return result;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public ExamResult getExamResult(int studentId) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " - Read operation started.");
        ExamResult examResult = examResultRepository.findById(studentId).get();
        System.out.println(Thread.currentThread().getName() + " - Mark read: " + examResult.getMark());

        Thread.sleep(4000);  // Just like in your original example

        ExamResult examResult2 = examResultRepository.findById(studentId).get();
        System.out.println(Thread.currentThread().getName() + " - New mark read: " + examResult2.getMark());

        return examResult2;
    }

    public ExamResult update(int studentId, int newMark) {
        ExamResult examResult = examResultRepository.findById(studentId).get();
        examResult.setMark(newMark);
        return examResultRepository.save(examResult);
    }

    public ExamResult read(int studentId) {
        return examResultRepository.findById(studentId).get();
    }

}
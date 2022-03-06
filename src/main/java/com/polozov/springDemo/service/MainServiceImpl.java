package com.polozov.springDemo.service;

import com.polozov.springDemo.entity.Student;
import com.polozov.springDemo.entity.StudentAnswer;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class MainServiceImpl implements MainService {

    private final StudentService studentService;
    private final ExamService examService;

    public MainServiceImpl(StudentService studentService, ExamService examService) {
        this.studentService = studentService;
        this.examService = examService;
    }

    @Override
    public void startApp() {
        Student student = this.studentService.save();
        Set<StudentAnswer> studentAnswers = examService.examProcessing();
        student.setAnswers(studentAnswers);
        examService.getResult(examService.checkExamResult(studentAnswers));
    }
}

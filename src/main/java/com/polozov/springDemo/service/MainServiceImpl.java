package com.polozov.springDemo.service;

import com.polozov.springDemo.entity.ExamResult;
import com.polozov.springDemo.entity.Student;
import com.polozov.springDemo.entity.StudentAnswer;
import com.polozov.springDemo.view.DataPrinter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class MainServiceImpl implements MainService {

    private final String boundQuantityOfRightAnswers;
    private final StudentService studentService;
    private final ExamService examService;
    private final DataPrinter printer;

    public MainServiceImpl(StudentService studentService, ExamService examService, DataPrinter printer, @Value("${count}") String boundQuantityOfRightAnswers) {
        this.studentService = studentService;
        this.examService = examService;
        this.printer = printer;
        this.boundQuantityOfRightAnswers = boundQuantityOfRightAnswers;
    }

    @Override
    public void startApp() {
        Student student = this.studentService.fillStudent();
        Set<StudentAnswer> studentAnswers = examService.processExam();
        ExamResult result = ExamResult.builder()
                .student(student)
                .studentAnswer(studentAnswers)
                .build();

        printResult(checkExamResult(result.getStudentAnswer()));
    }

    private boolean checkExamResult(Set<StudentAnswer> studentAnswers) {
        int countOfRightAnswers = 0;
        for (StudentAnswer sa : studentAnswers) {
            if (sa.getQuestion().getRightAnswer().equalsIgnoreCase(sa.getAnswer())) {
                countOfRightAnswers++;
            }
        }
        return countOfRightAnswers >= Integer.parseInt(boundQuantityOfRightAnswers);
    }

    private void printResult(boolean result) {
        printer.printResult(result);
    }
}

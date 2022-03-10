package com.polozov.springDemo.service;

import com.polozov.springDemo.entity.ExamResult;
import com.polozov.springDemo.entity.Student;
import com.polozov.springDemo.entity.StudentAnswer;
import com.polozov.springDemo.view.DataPrinter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Formatter;
import java.util.List;
import java.util.Set;

@Service
public class MainServiceImpl implements MainService {

    private final int boundQuantityOfRightAnswers;
    private final StudentService studentService;
    private final ExamService examService;
    private final DataPrinter printer;

    public MainServiceImpl(StudentService studentService, ExamService examService, DataPrinter printer, @Value("${countOfRightAnswer}") Integer boundQuantityOfRightAnswers) {
        this.studentService = studentService;
        this.examService = examService;
        this.printer = printer;
        this.boundQuantityOfRightAnswers = boundQuantityOfRightAnswers;
    }

    @Override
    public void startApp() {
        Student student = this.studentService.readStudent();
        ExamResult result  = examService.processExam();
        result.setStudent(student);

        boolean isPassed = checkExamResult(result.getCorrectStudentAnswer());
        Formatter resultString = new Formatter();
        resultString.format("%s, вы ответили правильно на %d вопросов, экзамен %s",
                result.getStudent().getFirstName(),
                result.getCorrectStudentAnswer().size(),
                isPassed ? "пройден" : "не пройден");
        printer.printLine(resultString.toString());
    }

    private boolean checkExamResult(Set<StudentAnswer> studentAnswers) {
        return studentAnswers.size() >= boundQuantityOfRightAnswers;
    }
}

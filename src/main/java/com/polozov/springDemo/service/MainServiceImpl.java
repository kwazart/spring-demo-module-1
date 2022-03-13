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

    private final int boundQuantityOfRightAnswers;
    private final StudentService studentService;
    private final ExamService examService;
    private final DataPrinter printer;
    private final InputAndLocaleServiceFacade inputAndLocaleServiceFacade;

    public MainServiceImpl(StudentService studentService,
                           ExamService examService,
                           DataPrinter printer,
                           @Value("${countOfRightAnswer}") Integer boundQuantityOfRightAnswers,
                           InputAndLocaleServiceFacade inputAndLocaleServiceFacade) {
        this.studentService = studentService;
        this.examService = examService;
        this.printer = printer;
        this.boundQuantityOfRightAnswers = boundQuantityOfRightAnswers;
        this.inputAndLocaleServiceFacade = inputAndLocaleServiceFacade;
    }

    @Override
    public void startApp() {
        Student student = this.studentService.readStudent();
        ExamResult result  = examService.processExam();
        result.setStudent(student);

        boolean isPassed = checkExamResult(result.getCorrectStudentAnswer());

        String code = getNameProperty(isPassed);

        String resultString = inputAndLocaleServiceFacade.getLocaleMessage(code, result.getStudent().getFirstName(),
                String.valueOf(result.getCorrectStudentAnswer().size()));
        printer.printLine(resultString);
    }

    private boolean checkExamResult(Set<StudentAnswer> studentAnswers) {
        return studentAnswers.size() >= boundQuantityOfRightAnswers;
    }

    private String getNameProperty(boolean isPassed) {
        return String.format("exam.result.%s", isPassed ? "success" : "failed");
    }
}

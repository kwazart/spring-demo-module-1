package com.polozov.springDemo.service;

import com.polozov.springDemo.entity.ExamResult;
import com.polozov.springDemo.entity.Student;
import com.polozov.springDemo.entity.StudentAnswer;
import com.polozov.springDemo.util.LocaleUtil;
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
    private final LocaleUtil localeUtil;

    public MainServiceImpl(StudentService studentService,
                           ExamService examService,
                           DataPrinter printer,
                           @Value("${countOfRightAnswer}") Integer boundQuantityOfRightAnswers,
                           LocaleUtil localeUtil) {
        this.studentService = studentService;
        this.examService = examService;
        this.printer = printer;
        this.boundQuantityOfRightAnswers = boundQuantityOfRightAnswers;
        this.localeUtil = localeUtil;
    }

    @Override
    public void startApp() {
        Student student = this.studentService.readStudent();
        ExamResult result  = examService.processExam();
        result.setStudent(student);

        boolean isPassed = checkExamResult(result.getCorrectStudentAnswer());

        String code = "exam.result." + (isPassed ? "success" : "failed");

        String resultString = localeUtil.getLocaleMessage(code, new String[] {
                result.getStudent().getFirstName(),
                String.valueOf(result.getCorrectStudentAnswer().size())});
        printer.printLine(resultString);
    }

    private boolean checkExamResult(Set<StudentAnswer> studentAnswers) {
        return studentAnswers.size() >= boundQuantityOfRightAnswers;
    }
}

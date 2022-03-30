package com.polozov.springDemo.service;

import com.polozov.springDemo.entity.ExamResult;
import com.polozov.springDemo.entity.Student;
import com.polozov.springDemo.entity.StudentAnswer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;

import java.util.Set;

@ShellComponent
public class MainShellService {

    private final int boundQuantityOfRightAnswers;
    private final ExamService examService;
    private final InputAndLocaleServiceFacade inputAndLocaleServiceFacade;

    private Student student;
    private String resultString;

    public MainShellService(StudentService studentService,
                            ExamService examService,
                            @Value("${countOfRightAnswer}") Integer boundQuantityOfRightAnswers,
                            InputAndLocaleServiceFacade inputAndLocaleServiceFacade) {
        this.examService = examService;
        this.boundQuantityOfRightAnswers = boundQuantityOfRightAnswers;
        this.inputAndLocaleServiceFacade = inputAndLocaleServiceFacade;
    }

    @ShellMethod(value = "Registration command", key = {"r", "reg", "registration"})
    public String registration(@ShellOption(defaultValue = "unknown") String firstName,
                               @ShellOption(defaultValue = "unknown") String lastName) {
        this.student = new Student(firstName, lastName);
        return String.format("Добро пожаловать: %s", student.getFirstName());
    }

    @ShellMethod(value = "Start exam command", key = {"start", "start-exam"})
    @ShellMethodAvailability(value = "isStartExamCommandAvailable")
    public String startExam() {
        ExamResult result = examService.processExam();
        result.setStudent(this.student);
        boolean isPassed = checkExamResult(result.getCorrectStudentAnswer());
        String code = getNameProperty(isPassed);
        this.resultString = inputAndLocaleServiceFacade.getLocaleMessage(code, result.getStudent().getFirstName(),
                String.valueOf(result.getCorrectStudentAnswer().size()));
        return "Экзамен завершен";
    }

    @ShellMethod(value = "Print result command", key = {"pr", "res", "result"})
    @ShellMethodAvailability(value = "isPrintResultCommandAvailable")
    public String result() {
        return resultString;
    }

    private Availability isStartExamCommandAvailable() {
        return this.student == null? Availability.unavailable("Сначала залогиньтесь (\"r\", \"reg\", \"registration\")"): Availability.available();
    }

    private Availability isPrintResultCommandAvailable() {
        return this.resultString == null? Availability.unavailable("Сначала пройдите экзамен (\"start\", \"start-exam\")"): Availability.available();
    }

    private boolean checkExamResult(Set<StudentAnswer> studentAnswers) {
        return studentAnswers.size() >= boundQuantityOfRightAnswers;
    }

    private String getNameProperty(boolean isPassed) {
        return String.format("exam.result.%s", isPassed ? "success" : "failed");
    }
}

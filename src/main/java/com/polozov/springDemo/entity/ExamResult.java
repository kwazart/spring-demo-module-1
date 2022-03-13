package com.polozov.springDemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamResult {
    private Student student;
    private Set<StudentAnswer> incorrectStudentAnswer;
    private Set<StudentAnswer> correctStudentAnswer;

    public ExamResult(Set<StudentAnswer> correctStudentAnswer, Set<StudentAnswer> incorrectStudentAnswer) {
        this.incorrectStudentAnswer = incorrectStudentAnswer;
        this.correctStudentAnswer = correctStudentAnswer;
    }
}

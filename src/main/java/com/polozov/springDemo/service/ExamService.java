package com.polozov.springDemo.service;

import com.polozov.springDemo.entity.StudentAnswer;

import java.util.Set;

public interface ExamService {
    Set<StudentAnswer> examProcessing();
    boolean checkExamResult(Set<StudentAnswer> studentAnswers);
    void getResult(boolean result);
}

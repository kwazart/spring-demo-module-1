package com.polozov.springDemo.service;

import com.polozov.springDemo.entity.StudentAnswer;

import java.util.Set;

public interface ExamService {
    Set<StudentAnswer> processExam();
}

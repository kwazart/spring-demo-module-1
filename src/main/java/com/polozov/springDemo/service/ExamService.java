package com.polozov.springDemo.service;

import com.polozov.springDemo.entity.StudentAnswer;

import java.util.List;
import java.util.Set;

public interface ExamService {
    List<Set<StudentAnswer>> processExam();
}

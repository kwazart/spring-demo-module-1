package com.polozov.springDemo.view;

import com.polozov.springDemo.entity.Question;

import java.util.List;

public interface DataPrinter {
    void printQuestionAndGetAnswer(Question questions);
    void printResult(boolean isExamPassed);
}

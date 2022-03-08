package com.polozov.springDemo.view;

import com.polozov.springDemo.entity.Question;

import java.util.List;

public interface DataPrinter {
    void printQuestion(String question);
    void printAnswer(String answer, char variant);
    void printGettingAnswer();
    void printResult(boolean isExamPassed);
}

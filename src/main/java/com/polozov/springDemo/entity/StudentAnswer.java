package com.polozov.springDemo.entity;

import lombok.Data;

@Data
public class StudentAnswer {
    private String answer;
    private Question question;

    public StudentAnswer(String answer, Question question) {
        this.answer = answer;
        this.question = question;
    }
}

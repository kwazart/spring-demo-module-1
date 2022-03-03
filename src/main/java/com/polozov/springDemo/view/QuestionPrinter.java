package com.polozov.springDemo.view;

import com.polozov.springDemo.entity.Question;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QuestionPrinter implements DataPrinter {
    public void printQuestionAndGetAnswer(List<Question> questions) {
        for (Question q : questions) {
            System.out.println("Question: " + q.getQuestion());
            if (!q.isHasFreeAnswer()) {
                char ch = 'a';
                for (String s : q.getAnswers()) {
                    System.out.println(ch++ + ". " + s);
                }
            }
        }
    }
}

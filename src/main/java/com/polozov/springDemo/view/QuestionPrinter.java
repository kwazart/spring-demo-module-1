package com.polozov.springDemo.view;

import com.polozov.springDemo.entity.Question;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QuestionPrinter implements DataPrinter {

    public void printQuestionAndGetAnswer(Question question) {
        if (!question.isHasFreeAnswer()) {
            char ch = 'a';
            for (String s : question.getAnswers()) {
                System.out.println(ch++ + ". " + s);
            }
        }
    }

    @Override
    public void printQuestion(String question) {
        System.out.println("\nQuestion: " + question);
    }

    @Override
    public void printAnswer(String answer, char variant) {
        System.out.println(variant + ". " + answer);
    }

    @Override
    public void printGettingAnswer() {
        System.out.print("Your answer: ");
    }

    @Override
    public void printResult(boolean isExamPassed) {
        System.out.println("=============================");
        if (isExamPassed) {
            System.out.println("===      Exam passed      ===");
        } else {
            System.out.println("===      Exam failed      ===");
        }
        System.out.println("=============================");
    }
}

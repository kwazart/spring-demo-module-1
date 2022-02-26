package com.polozov.springDemo.view;

import com.polozov.springDemo.entity.Question;

import java.util.List;
import java.util.Scanner;

public class QuestionPrinter {
    public void printQuestionAndGetAnswer(List<Question> questions) {
        for (Question q : questions) {
            System.out.println("Question: " + q.getQuestion());
            if (!q.isHasFreeAnswer()) {
                char ch = 'a';
                for (String s : q.getAnswers()) {
                    System.out.println(ch++ + ". " + s);
                }
            }
            Scanner scanner = new Scanner(System.in);
            System.out.print("Your answer: ");
            q.setStudentAnswer(scanner.nextLine());
        }
    }
}

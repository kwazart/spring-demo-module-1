package com.polozov.springDemo;

import com.polozov.springDemo.dao.CsvDaoImpl;
import com.polozov.springDemo.dao.CsvDao;
import com.polozov.springDemo.entity.Question;
import com.polozov.springDemo.view.QuestionPrinter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Scanner;

public class MainRunner {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        CsvDao dao = context.getBean("parserUtil", CsvDaoImpl.class);
        QuestionPrinter questionPrinter = context.getBean("questionPrinter", QuestionPrinter.class);

        List<Question> questions = dao.getQuestions();
        questionPrinter.printQuestionAndGetAnswer(questions);
    }
}

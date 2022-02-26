package com.polozov.springDemo.util;

import com.polozov.springDemo.entity.Question;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.Assert;

import java.util.*;

public class QuestionConverterUtilTest {

    private static ApplicationContext context;

    @BeforeAll
    public static void initContext() {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    // оценивается вроде только один тест
    @Test
    public void converterMustReturnCorrectQuestionWithoutAnswers() {
        QuestionConverterUtil converter = context.getBean("questionConverter", QuestionConverterUtil.class);

        String q = "Question";
        String a1 = "Answer1";
        String a2 = "Answer2";
        String a3 = "Answer3";

        List<String> line = new ArrayList<>();
        line.add(q);
        line.add(a1);
        line.add(a2);
        line.add(a3);

        Question question = Question.builder()
                .question(q)
                .answers(new HashSet<>(Set.of(a1, a2, a3)))
                .build();

        Assertions.assertEquals(question, converter.convertListStringToQuestion(line));
    }
}

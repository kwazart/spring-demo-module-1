package com.polozov.springDemo.util;

import com.polozov.springDemo.entity.Question;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class QuestionConverterUtilTest {

    @Test
    public void converterMustReturnCorrectQuestionWithoutAnswers() {
        QuestionConverter converter = new QuestionConverter();

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

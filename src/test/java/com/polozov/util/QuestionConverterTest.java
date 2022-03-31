package com.polozov.util;

import com.polozov.springDemo.entity.Question;
import com.polozov.springDemo.util.QuestionConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@SpringBootTest
public class QuestionConverterTest {

    private static final String STRING_QUESTION = "MainQuestion";
    private static final String STRING_RIGHT_ANSWER = "MainAnswer";
    private static final List<String> QUESTION_AND_ANSWER_TEMPLATE_1 = List.of(STRING_QUESTION, "Ответ-1", "Ответ-2", STRING_RIGHT_ANSWER);
    private static final List<String> QUESTION_AND_ANSWER_TEMPLATE_2 = List.of(STRING_QUESTION, "Ответ-1", "Ответ-2", STRING_RIGHT_ANSWER, STRING_RIGHT_ANSWER);
    private static final List<String> QUESTION_AND_ANSWER_TEMPLATE_3 = List.of(STRING_QUESTION, STRING_RIGHT_ANSWER);

    @ComponentScan("com.polozov.springDemo.util")
    @Configuration
    static class NestedTestConfiguration { }

    @Autowired
    private QuestionConverter converter;

    @DisplayName("Список строк корректно конвертируется в объект Question")
    @Test
    void shouldConvertListStringToQuestion() {
        Optional<Question> question1 = converter.convertListStringToQuestion(QUESTION_AND_ANSWER_TEMPLATE_1);
        Optional<Question> question2 = converter.convertListStringToQuestion(QUESTION_AND_ANSWER_TEMPLATE_2);
        Optional<Question> question3 = converter.convertListStringToQuestion(QUESTION_AND_ANSWER_TEMPLATE_2);
        Assertions.assertNotNull(question1.get());
        Assertions.assertNotNull(question2.get());
        Assertions.assertNotNull(question3.get());

    }

    @DisplayName("В каждой строке есть минимум вопрос и правильный вариант ответа")
    @Test
    void shouldReadStringHasAnswerAndRightQuestion() {
        Optional<Question> question1 = converter.convertListStringToQuestion(QUESTION_AND_ANSWER_TEMPLATE_1);
        Optional<Question> question2 = converter.convertListStringToQuestion(QUESTION_AND_ANSWER_TEMPLATE_2);
        Optional<Question> question3 = converter.convertListStringToQuestion(QUESTION_AND_ANSWER_TEMPLATE_3);
        for (Optional<Question> optionalQuestion : Arrays.asList(question1, question2, question3)) {
            if (optionalQuestion.isPresent()) {
                Question question = optionalQuestion.get();
                Assertions.assertEquals(question.getQuestion(), STRING_QUESTION);
                Assertions.assertEquals(question.getRightAnswer(), STRING_RIGHT_ANSWER);
            }
        }
    }

    @DisplayName("В вопросе с вариантом выбора есть правильный ответ")
    @Test
    void shouldHasRightAnswerInAnswersVariant() {
        Optional<Question> question1 = converter.convertListStringToQuestion(QUESTION_AND_ANSWER_TEMPLATE_1);
        Optional<Question> question2 = converter.convertListStringToQuestion(QUESTION_AND_ANSWER_TEMPLATE_2);

        // не содержит правильного ответа
        if (question1.isPresent()) {
            Question question = question1.get();
            if (!question.isHasFreeAnswer()) {
                Set<String> answers = question.getAnswers();
                Assertions.assertFalse(answers.contains(STRING_RIGHT_ANSWER));
            }
        }

        // содержит правильный ответ
        if (question2.isPresent()) {
            Question question = question2.get();
            if (!question.isHasFreeAnswer()) {
                Set<String> answers = question.getAnswers();
                Assertions.assertTrue(answers.contains(STRING_RIGHT_ANSWER));
            }
        }
    }
}

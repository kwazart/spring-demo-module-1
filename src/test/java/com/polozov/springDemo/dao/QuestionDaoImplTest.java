package com.polozov.springDemo.dao;

import com.polozov.springDemo.util.QuestionConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;

@DisplayName("Класс DAO")
public class QuestionDaoImplTest {

    @Mock
    private QuestionConverter converter;

    private final String FILENAME = "/questions.csv";

    @DisplayName("Корректное создание конструктором")
    @Test
    void shouldHaveCorrectConstructor() {
        QuestionDao dao = new QuestionDaoImpl(converter, FILENAME);
        Assertions.assertNotNull(dao);
    }

    @DisplayName("Корректное создание списка строк")
    @Test
    void shouldHaveCorrectQuestionList() {
        QuestionDaoImpl dao = new QuestionDaoImpl(converter, FILENAME);
        List<List<String>> data = dao.getData();
        Assertions.assertNotNull(data);
    }

    @DisplayName("Количество считанных вопросов больше нуля")
    @Test
    void shouldHaveQuestionListMoreThanZero() {
        QuestionDaoImpl dao = new QuestionDaoImpl(converter, FILENAME);
        Assertions.assertTrue(dao.getData().size() > 0);
    }
}

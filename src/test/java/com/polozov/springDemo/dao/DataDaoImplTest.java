package com.polozov.springDemo.dao;

import com.polozov.springDemo.entity.Question;
import com.polozov.springDemo.util.QuestionConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;

@DisplayName("Класс DAO")
public class DataDaoImplTest {

    @Mock
    private QuestionConverter converter;

    private static final String FILENAME = "/questions.csv";

    @DisplayName("Корректное создание конструктором")
    @Test
    void shouldHaveCorrectConstructor() {
        DataDao dao = new DataDaoImpl(converter);
        Assertions.assertNotNull(dao);
    }

    @DisplayName("Корректное создание списка строк")
    @Test
    void shouldHaveCorrectQuestionList() {
        DataDao dao = new DataDaoImpl(converter);
        List<List<String>> dataLines = dao.getStringQuestion(FILENAME);
        Assertions.assertNotNull(dataLines);
    }

    @DisplayName("Количество считанных вопросов больше нуля")
    @Test
    void shouldHaveQuestionListMoreThanZero() {
        DataDao dao = new DataDaoImpl(converter);
        Assertions.assertTrue(dao.getStringQuestion(FILENAME).size() > 0);
    }
}

package com.polozov.springDemo.dao;

import com.polozov.springDemo.entity.Question;
import com.polozov.springDemo.util.QuestionConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

@DisplayName("Класс DAO")
public class DataDaoImplTest {

    @Mock
    private QuestionConverter converter;

    private final String FILENAME = "/questions.csv";

    @DisplayName("Корректное создание конструктором")
    @Test
    void shouldHaveCorrectConstructor() {
        DataDao dao = new DataDaoImpl(converter, FILENAME);
        Assertions.assertNotNull(dao);
    }

    @DisplayName("Корректное создание списка строк")
    @Test
    void shouldHaveCorrectQuestionList() {
        DataDaoImpl dao = new DataDaoImpl(converter, FILENAME);
        List<List<String>> data = dao.getData();
        Assertions.assertNotNull(data);
    }

    @DisplayName("Количество считанных вопросов больше нуля")
    @Test
    void shouldHaveQuestionListMoreThanZero() {
        DataDaoImpl dao = new DataDaoImpl(converter, FILENAME);
        Assertions.assertTrue(dao.getData().size() > 0);
    }
}

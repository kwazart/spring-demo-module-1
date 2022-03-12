package com.polozov.dao;

import com.polozov.springDemo.dao.QuestionDao;
import com.polozov.springDemo.dao.QuestionDaoImpl;
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

    private final String FILENAME = "/additional/questions-";
    private final String LOCALE = "en";
    private final String FILEFORMAT = ".csv";

    @DisplayName("Корректное создание конструктором")
    @Test
    void shouldHaveCorrectConstructor() {
        QuestionDao dao = new QuestionDaoImpl(converter, FILENAME, LOCALE, FILEFORMAT);
        Assertions.assertNotNull(dao);
    }

    @DisplayName("Корректное создание списка строк")
    @Test
    void shouldHaveCorrectQuestionList() {
        QuestionDaoImpl dao = new QuestionDaoImpl(converter, FILENAME, LOCALE, FILEFORMAT);
        List<List<String>> data = dao.getData();
        Assertions.assertNotNull(data);
    }

    @DisplayName("Количество считанных вопросов больше нуля")
    @Test
    void shouldHaveQuestionListMoreThanZero() {
        QuestionDaoImpl dao = new QuestionDaoImpl(converter, FILENAME, LOCALE, FILEFORMAT);
        Assertions.assertTrue(dao.getData().size() > 0);
    }
}

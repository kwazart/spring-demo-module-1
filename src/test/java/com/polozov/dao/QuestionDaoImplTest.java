package com.polozov.dao;

import com.polozov.springDemo.dao.QuestionDao;
import com.polozov.springDemo.dao.QuestionDaoImpl;
import com.polozov.springDemo.service.FileNameProvider;
import com.polozov.springDemo.service.FileNameProviderImpl;
import com.polozov.springDemo.util.QuestionConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;

@DisplayName("Класс DAO")
public class QuestionDaoImplTest {

    private static QuestionConverter converter;
    private static FileNameProvider nameProvider;

    @BeforeAll
    public static void initDependencies() {
        converter = new QuestionConverter();
        nameProvider = new FileNameProviderImpl(FILENAME, LOCALE, FILEFORMAT);
    }

    private static final String FILENAME = "/additional/questions-";
    private static final String LOCALE = "en";
    private static final String FILEFORMAT = ".csv";

    @DisplayName("Корректное создание конструктором")
    @Test
    void shouldHaveCorrectConstructor() {
        QuestionDao dao = new QuestionDaoImpl(converter, nameProvider);
        Assertions.assertNotNull(dao);
    }

    @DisplayName("Корректное создание списка строк")
    @Test
    void shouldHaveCorrectQuestionList() {
        QuestionDaoImpl dao = new QuestionDaoImpl(converter, nameProvider);
        List<List<String>> data = dao.getData();
        Assertions.assertNotNull(data);
    }

    @DisplayName("Количество считанных вопросов больше нуля")
    @Test
    void shouldHaveQuestionListMoreThanZero() {
        QuestionDaoImpl dao = new QuestionDaoImpl(converter, nameProvider);
        Assertions.assertTrue(dao.getData().size() > 0);
    }
}

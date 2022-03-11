package com.polozov.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@DisplayName("Class - Question")
public class QuestionTest {

    @DisplayName("Корректное создание конструктором пустого объекта")
    @Test
    void shouldHaveCorrectConstructorCreateEmptyObject() {
        QuestionTest questionTest = new QuestionTest();
        Assertions.assertNotNull(questionTest);
    }
}

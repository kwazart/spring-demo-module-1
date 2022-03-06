package com.polozov.springDemo.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Class - Question")
public class Question {

    @DisplayName("Корректное создание конструктором пустого объекта")
    @Test
    void shouldHaveCorrectConstructorCreateEmptyObject() {
        Question question = new Question();
        Assertions.assertNotNull(question);
    }
}

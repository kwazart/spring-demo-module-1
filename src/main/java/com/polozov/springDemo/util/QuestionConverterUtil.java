package com.polozov.springDemo.util;

import com.polozov.springDemo.entity.Question;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class QuestionConverterUtil {

    /**
     * Метод обрабатывает считанную строку.
     * Рассматриваются 2 случая: варианты ответа есть и вариантов ответа в строке нет
     * @param line считанная из файла строка
     * @return новый вопрос
     */
    public Question convertListStringToQuestion(List<String> line) {
        if (line.size() == 1) {
            return Question.builder()
                    .question(line.get(0))
                    .hasFreeAnswer(true)
                    .build();
        } else {
            Set<String> answers = new HashSet<>();
            for (int i = 1; i < line.size(); i++) {
                answers.add(line.get(i));
            }
            return Question.builder()
                    .question(line.get(0))
                    .answers(answers)
                    .hasFreeAnswer(false)
                    .build();
        }
    }
}

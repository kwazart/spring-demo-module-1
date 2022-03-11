package com.polozov.springDemo.util;

import com.polozov.springDemo.entity.Question;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class QuestionConverter {

    /**
     * Метод обрабатывает считанную строку.
     * Рассматриваются 2 случая: варианты ответа есть и вариантов ответа в строке нет.
     * Последнее значение в строке является правильным ответом на вопрос
     * @param line считанная из файла строка
     * @return новый вопрос
     */
    public Optional<Question> convertListStringToQuestion(List<String> line) {
        Question question = null;
        if (line.size() == 2) {
            question = Question.builder()
                    .question(line.get(0))
                    .hasFreeAnswer(true)
                    .rightAnswer(line.get(1))
                    .build();
        } else if (line.size() > 2){
            Set<String> answers = new HashSet<>();
            for (int i = 1; i < line.size() -1; i++) {
                answers.add(line.get(i));
            }
            question = Question.builder()
                    .question(line.get(0))
                    .answers(answers)
                    .hasFreeAnswer(false)
                    .rightAnswer(line.get(line.size() - 1))
                    .build();
        }
        return Optional.ofNullable(question);
    }

    public List<Question> convertStringsToQuestions(List<List<String>> inputLines) {
        return inputLines.stream()
                .map(this::convertListStringToQuestion)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }
}

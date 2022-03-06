package com.polozov.springDemo.service;

import com.polozov.springDemo.dao.DataDao;
import com.polozov.springDemo.entity.Question;
import com.polozov.springDemo.entity.StudentAnswer;
import com.polozov.springDemo.view.DataPrinter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ExamServiceImpl implements ExamService {

    private final DataDao dao;
    private final DataPrinter printer;
    private final DataInput dataInput;

    @Value("${count}")
    private String boundQuantityOfRightAnswers;

    public ExamServiceImpl(DataDao dao, DataPrinter printer, DataInput dataInput) {
        this.dao = dao;
        this.printer = printer;
        this.dataInput = dataInput;
    }

    @Override
    public Set<StudentAnswer> examProcessing() {
        Set<StudentAnswer> answerSet = new HashSet<>();
        List<List<String>> stringData = dao.getStringQuestion(null);
        if (stringData.size() > 0) {
            List<Question> questions = dao.convertStringsToQuestions(stringData);
            for (Question question : questions) {
                printer.printQuestionAndGetAnswer(question);
                String answer = dataInput.getData();
                StudentAnswer studentAnswer = StudentAnswer.builder()
                        .question(question)
                        .answer(answer)
                        .build();
                answerSet.add(studentAnswer);
            }
        }
        return answerSet;
    }

    @Override
    public boolean checkExamResult(Set<StudentAnswer> studentAnswers) {
        int countOfRightAnswers = 0;
        for (StudentAnswer sa : studentAnswers) {
            if (sa.getQuestion().getRightAnswer().equalsIgnoreCase(sa.getAnswer())) {
                countOfRightAnswers++;
            }
        }
        return countOfRightAnswers >= Integer.parseInt(boundQuantityOfRightAnswers);
    }

    @Override
    public void getResult(boolean result) {
        printer.printResult(result);
    }
}

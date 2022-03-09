package com.polozov.springDemo.service;

import com.polozov.springDemo.dao.QuestionDao;
import com.polozov.springDemo.entity.Question;
import com.polozov.springDemo.entity.StudentAnswer;
import com.polozov.springDemo.view.DataPrinter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ExamServiceImpl implements ExamService {

    private final QuestionDao dao;
    private final DataPrinter printer;
    private final DataInput dataInput;

    public ExamServiceImpl(QuestionDao dao, DataPrinter printer, DataInput dataInput) {
        this.dao = dao;
        this.printer = printer;
        this.dataInput = dataInput;
    }

    @Override
    public List<Set<StudentAnswer>> processExam() {
        List<Set<StudentAnswer>> answerList = new ArrayList<>();
        Set<StudentAnswer> incorrectAnswerSet = new HashSet<>();
        Set<StudentAnswer> correctAnswerSet = new HashSet<>();
        List<Question> questionList = dao.getQuestions();
        if (questionList.size() > 0) {
            for (Question question : questionList) {
                printer.printLine("Question: " + question.getQuestion());
                if (!question.isHasFreeAnswer()) {
                    char startVariant = 'a';
                    for (String answer : question.getAnswers()) {
                        printer.printLine(startVariant++ + ". " + answer);
                    }
                }
                printer.printShortLine("Your answer: ");
                String answer = dataInput.getData();
                StudentAnswer studentAnswer = StudentAnswer.builder()
                        .question(question)
                        .answer(answer)
                        .build();

                if (question.getRightAnswer().equalsIgnoreCase(answer)) {
                    correctAnswerSet.add(studentAnswer);
                } else {
                    incorrectAnswerSet.add(studentAnswer);
                }
            }
        }

        answerList.add(correctAnswerSet);
        answerList.add(incorrectAnswerSet);
        return answerList;
    }


}

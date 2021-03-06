package com.polozov.springDemo.service;

import com.polozov.springDemo.dao.QuestionDao;
import com.polozov.springDemo.entity.ExamResult;
import com.polozov.springDemo.entity.Question;
import com.polozov.springDemo.entity.StudentAnswer;
import com.polozov.springDemo.view.DataPrinter;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ExamServiceImpl implements ExamService {

    private final QuestionDao dao;
    private final DataPrinter printer;
    private final InputAndLocaleServiceFacade inputAndLocaleServiceFacade;

    public ExamServiceImpl(QuestionDao dao, DataPrinter printer, InputAndLocaleServiceFacade inputAndLocaleServiceFacade) {
        this.dao = dao;
        this.printer = printer;
        this.inputAndLocaleServiceFacade = inputAndLocaleServiceFacade;
    }

    @Override
    public ExamResult processExam() {

        Set<StudentAnswer> incorrectAnswerSet = new HashSet<>();
        Set<StudentAnswer> correctAnswerSet = new HashSet<>();
        List<Question> questionList = dao.getQuestions();
        if (questionList.size() > 0) {
            for (Question question : questionList) {
                printer.printLine(inputAndLocaleServiceFacade.getLocaleMessage("question") + ": " + question.getQuestion());
                if (!question.isHasFreeAnswer()) {
                    char startVariant = 'a';
                    for (String answer : question.getAnswers()) {
                        printer.printLine(startVariant++ + ". " + answer);
                    }
                }
                printer.printShortLine(inputAndLocaleServiceFacade.getLocaleMessage("answer") + ": ");
                String answer = inputAndLocaleServiceFacade.getData();
                StudentAnswer studentAnswer = new StudentAnswer(answer, question);

                if (question.getRightAnswer().equalsIgnoreCase(answer)) {
                    correctAnswerSet.add(studentAnswer);
                } else {
                    incorrectAnswerSet.add(studentAnswer);
                }
            }
        }

        return new ExamResult(correctAnswerSet, incorrectAnswerSet);
    }


}

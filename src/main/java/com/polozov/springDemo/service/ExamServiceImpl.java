package com.polozov.springDemo.service;

import com.polozov.springDemo.dao.DataDao;
import com.polozov.springDemo.entity.Question;
import com.polozov.springDemo.entity.StudentAnswer;
import com.polozov.springDemo.view.DataPrinter;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ExamServiceImpl implements ExamService {

    private final DataDao dao;
    private final DataPrinter printer;
    private final DataInput dataInput;

    public ExamServiceImpl(DataDao dao, DataPrinter printer, DataInput dataInput) {
        this.dao = dao;
        this.printer = printer;
        this.dataInput = dataInput;
    }

    @Override
    public Set<StudentAnswer> processExam() {
        Set<StudentAnswer> answerSet = new HashSet<>();
        List<Question> questionList = dao.getQuestions();
        if (questionList.size() > 0) {
            for (Question question : questionList) {
                printer.printQuestion(question.getQuestion());
                if (!question.isHasFreeAnswer()) {
                    char startVariant = 'a';
                    for (String answer : question.getAnswers()) {
                        printer.printAnswer(answer, startVariant++);
                    }
                }
                printer.printGettingAnswer();
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


}

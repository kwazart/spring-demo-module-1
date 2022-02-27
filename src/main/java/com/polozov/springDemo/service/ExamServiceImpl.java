package com.polozov.springDemo.service;

import com.polozov.springDemo.dao.DataDao;
import com.polozov.springDemo.entity.Question;
import com.polozov.springDemo.view.DataPrinter;

import java.util.List;

public class ExamServiceImpl implements ExamService {

    private DataDao dao;
    private DataPrinter printer;

    public ExamServiceImpl(DataDao dao, DataPrinter printer) {
        this.dao = dao;
        this.printer = printer;
    }

    @Override
    public void startExam() {
        List<Question> questions = dao.getQuestions();
        printer.printQuestionAndGetAnswer(questions);
    }
}

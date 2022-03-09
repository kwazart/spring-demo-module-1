package com.polozov.springDemo.dao;

import com.polozov.springDemo.entity.Question;

import java.util.List;

public interface QuestionDao {
    List<Question> getQuestions();
}

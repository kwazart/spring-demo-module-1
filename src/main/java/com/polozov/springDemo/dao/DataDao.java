package com.polozov.springDemo.dao;

import com.polozov.springDemo.entity.Question;

import java.util.List;

public interface DataDao {
    List<Question> getQuestions();
    List<Question> convertStringsToQuestions(List<List<String>> inputLines);
}

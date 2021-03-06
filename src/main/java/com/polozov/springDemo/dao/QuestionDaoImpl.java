package com.polozov.springDemo.dao;


import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.polozov.springDemo.entity.Question;
import com.polozov.springDemo.exception.QuestionsIOException;
import com.polozov.springDemo.exception.QuestionsLoadingException;
import com.polozov.springDemo.service.FileNameProvider;
import com.polozov.springDemo.util.QuestionConverter;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class QuestionDaoImpl implements QuestionDao {

    private final QuestionConverter converter;
    private final String filename;

    public QuestionDaoImpl(QuestionConverter converter, FileNameProvider nameProvider) {
        this.converter = converter;
        this.filename = nameProvider.getFilename();
    }

    public List<Question> getQuestions() {
        List<List<String>> questionAndAnswersList = getData();
        return converter.convertStringsToQuestions(questionAndAnswersList);
    }

    public List<List<String>> getData() {
        List<List<String>> questionAndAnswersList = new ArrayList<>();

        InputStream in = getClass().getResourceAsStream(filename);
        if (in != null) {
            InputStreamReader reader = new InputStreamReader(in, StandardCharsets.UTF_8);

            try (CSVReader rdr = new CSVReader(reader)){
                String[] nextLine;
                while ((nextLine = rdr.readNext()) != null) {
                    questionAndAnswersList.add(Arrays.asList(nextLine));
                }
            } catch (CsvValidationException e) {
                throw new QuestionsLoadingException(e);
            } catch (IOException e) {
                throw new QuestionsIOException(e);
            }
        }
        return questionAndAnswersList;
    }
}

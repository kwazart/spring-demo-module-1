package com.polozov.springDemo.dao;


import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.polozov.springDemo.entity.Question;
import com.polozov.springDemo.util.QuestionConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class DataDaoImpl implements DataDao {

    private final String fileName;
    private final QuestionConverter converter;

    public DataDaoImpl(QuestionConverter converter, @Value("${filename}") String fileName) {
        this.converter = converter;
        this.fileName = fileName;
    }

    public List<Question> getQuestions() {
        List<List<String>> questionAndAnswersList = getData();
        return convertStringsToQuestions(questionAndAnswersList);
    }

    public List<List<String>> getData() {
        List<List<String>> questionAndAnswersList = new ArrayList<>();

        InputStream in = getClass().getResourceAsStream(fileName);
        if (in != null) {
            InputStreamReader reader = new InputStreamReader(in, StandardCharsets.UTF_8);

            try (CSVReader rdr = new CSVReader(reader)){
                String[] nextLine;
                while ((nextLine = rdr.readNext()) != null) {
                    questionAndAnswersList.add(Arrays.asList(nextLine));
                }
            } catch (CsvValidationException | IOException e) {
                e.printStackTrace();
            }
        }
        return questionAndAnswersList;
    }


    public List<Question> convertStringsToQuestions(List<List<String>> inputLines) {
        return inputLines.stream()
                .map(converter::convertListStringToQuestion)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }
}

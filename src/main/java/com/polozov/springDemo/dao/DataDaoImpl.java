package com.polozov.springDemo.dao;


import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.polozov.springDemo.entity.Question;
import com.polozov.springDemo.util.QuestionConverter;
import lombok.AllArgsConstructor;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
public class DataDaoImpl implements DataDao {

    private final String fileName;
    private final QuestionConverter converter;

    public List<Question> getQuestions() {
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
            return questionAndAnswersList.stream()
                    .map(converter::convertListStringToQuestion)
                    .collect(Collectors.toList());
        }

        return new ArrayList<>();
    }

}

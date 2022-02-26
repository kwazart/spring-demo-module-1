package com.polozov.springDemo.dao;


import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.polozov.springDemo.entity.Question;
import com.polozov.springDemo.util.QuestionConverterUtil;
import lombok.AllArgsConstructor;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@AllArgsConstructor
public class CsvDaoImpl implements CsvDao {

    private final String fileName;
    // делаю чтобы потренироваться (так как в утильном классе сделал бы статику)
    private final QuestionConverterUtil converter;

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
            } catch (FileNotFoundException e) {
                System.err.println("File " + fileName + " not found");
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (CsvValidationException e) {
                System.err.println("Could not read file");
                e.printStackTrace();
            }
            return questionAndAnswersList.stream()
                    .map(converter::convertListStringToQuestion)
                    .collect(Collectors.toList());
        }

        return new ArrayList<>();
    }

}

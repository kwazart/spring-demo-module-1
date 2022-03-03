package com.polozov.springDemo.config;

import com.polozov.springDemo.dao.DataDao;
import com.polozov.springDemo.dao.DataDaoImpl;
import com.polozov.springDemo.service.ExamService;
import com.polozov.springDemo.service.ExamServiceImpl;
import com.polozov.springDemo.util.QuestionConverter;
import com.polozov.springDemo.view.QuestionPrinter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Value("/questions.csv")
    private String pathToFile;

    @Bean
    public DataDao dataDAO(QuestionConverter converter) {
        return new DataDaoImpl(pathToFile, converter);
    }

    @Bean
    public ExamService examService(DataDao dao, QuestionPrinter printer) {
        return new ExamServiceImpl(dao, printer);
    }
}

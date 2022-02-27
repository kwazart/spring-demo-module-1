package com.polozov.springDemo;

import com.polozov.springDemo.service.ExamService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainRunner {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        ExamService service = context.getBean("examService", ExamService.class);

        service.startExam();
    }
}

package com.polozov.springDemo;

import com.polozov.springDemo.service.ExamService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@ComponentScan
public class MainRunner {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(MainRunner.class);
        ExamService service = context.getBean("examService", ExamService.class);

        service.startExam();
    }
}

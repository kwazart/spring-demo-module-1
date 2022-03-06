package com.polozov.springDemo;

import com.polozov.springDemo.service.MainService;
import com.polozov.springDemo.service.MainServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@ComponentScan
//@PropertySource("classpath:app.properties") // не работает хотя файл есть
public class MainRunner {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(MainRunner.class);
        MainService service = context.getBean(MainServiceImpl.class);

        service.startApp();
    }
}

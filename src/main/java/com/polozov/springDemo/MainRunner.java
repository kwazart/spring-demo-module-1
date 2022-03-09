package com.polozov.springDemo;

import com.polozov.springDemo.service.MainService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@ComponentScan
@PropertySource("classpath:app.properties")
public class MainRunner {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(MainRunner.class);
        MainService service = context.getBean(MainService.class);

        service.startApp();
    }
}

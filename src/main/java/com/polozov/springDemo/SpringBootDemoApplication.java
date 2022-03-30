package com.polozov.springDemo;

import com.polozov.springDemo.service.MainService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class SpringBootDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDemoApplication.class, args);
//		ApplicationContext context = new AnnotationConfigApplicationContext(SpringBootDemoApplication.class);
//		MainService service = context.getBean(MainService.class);
//
//		service.startApp();
	}

}

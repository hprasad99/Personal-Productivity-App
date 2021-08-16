package com.cap.ppa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages="com.cap.ppa")
public class PersonalProductivityApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonalProductivityApplication.class, args);
	}

}

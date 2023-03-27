package com.third_project.third_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ThirdProjectApplication {
	public static void main(String[] args) {
		SpringApplication.run(ThirdProjectApplication.class, args);
	}

}

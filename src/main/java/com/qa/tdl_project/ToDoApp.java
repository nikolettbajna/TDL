package com.qa.tdl_project;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import org.springframework.boot.SpringApplication;

@SpringBootApplication
@EnableWebMvc
public class ToDoApp {

	public static void main(String[] args) {
		SpringApplication.run(ToDoApp.class, args);

	}

}

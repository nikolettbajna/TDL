package com.qa.tdl_project.rest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.tdl_project.services.TaskService;

@RestController
@CrossOrigin
@RequestMapping("/tasks")
public class TaskController {
	
	private TaskService service;

}
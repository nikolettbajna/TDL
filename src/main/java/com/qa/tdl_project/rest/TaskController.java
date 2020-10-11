package com.qa.tdl_project.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.tdl_project.dto.TaskDTO;
import com.qa.tdl_project.presistence.domain.Task;
import com.qa.tdl_project.services.TaskService;

@RestController
@CrossOrigin
@RequestMapping("/tasks")
public class TaskController {
	
	private TaskService service;
	
	@Autowired
	public TaskController(TaskService service) {
		super();
		this.service = service;
	}
	
	//view a specific task by id
	@GetMapping("/view/{id}")
	public ResponseEntity<TaskDTO> viewTDLById(@PathVariable Long id){
		return ResponseEntity.ok(this.service.viewTaskById(id));
	}
	
	//delete selected task
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<TaskDTO> deleteTaskById(@PathVariable Long id){
		return this.service.deleteTaskById(id)? new ResponseEntity<>(HttpStatus.NO_CONTENT): new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//update a task
	@PutMapping("/update/{id}")
	public ResponseEntity<TaskDTO> updateTaskById(@PathVariable Long id, @RequestBody TaskDTO taskdto){
		TaskDTO updated = this.service.updateTaskById(taskdto, id);
		return new ResponseEntity<>(updated, HttpStatus.ACCEPTED);
	}
	
	//create a new task
	@PostMapping("/create")
	public ResponseEntity<TaskDTO> create(@RequestBody Task task){
		return new ResponseEntity<>(this.service.createTask(task), HttpStatus.CREATED);
	}

}

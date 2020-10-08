package com.qa.tdl_project.rest;

import java.util.List;

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

import com.qa.tdl_project.dto.ToDoListDTO;
import com.qa.tdl_project.presistence.domain.ToDoList;
import com.qa.tdl_project.services.ToDoListService;

@RestController
@CrossOrigin
@RequestMapping("/todolists")
public class ToDoListController {
	
	private ToDoListService service;
	
	@Autowired
	public ToDoListController(ToDoListService service) {
		super();
		this.service = service;
	}
	
	//view all lists
	@GetMapping("/viewAll")
	public ResponseEntity<List<ToDoListDTO>> getAllToDoLists(){
		return ResponseEntity.ok(this.service.viewLists());
	}
	
	//delete selected to-do list
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ToDoListDTO> deleteToDoListById(@PathVariable Long id){
		return this.service.deleteToDoListById(id)? new ResponseEntity<>(HttpStatus.NO_CONTENT): new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//create a new to-do list
	@PostMapping("/create")
	public ResponseEntity<ToDoListDTO> create(@RequestBody ToDoList todolist){
		return new ResponseEntity<>(this.service.createToDoList(todolist), HttpStatus.CREATED);
	}
	
	//update a to-do list
	@PutMapping("/update/{id}")
	public ResponseEntity<ToDoListDTO> updateTDLById(@PathVariable Long id, @RequestBody ToDoListDTO todolistdto){
		ToDoListDTO updated = this.service.updateTDLById(todolistdto, id);
		return new ResponseEntity<>(updated, HttpStatus.ACCEPTED);
	}
	
}

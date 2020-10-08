package com.qa.tdl_project.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.tdl_project.dto.ToDoListDTO;
import com.qa.tdl_project.presistence.domain.ToDoList;
import com.qa.tdl_project.presistence.repo.ToDoListRepo;

@Service
public class ToDoListService {
	
	private ToDoListRepo repo;
	private ModelMapper mapper;
	
	@Autowired
	public ToDoListService(ToDoListRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}

	private ToDoListDTO mapToDTO(ToDoList tdl) {
		return this.mapper.map(tdl, ToDoListDTO.class);
	}
	
	//view all lists
	public List<ToDoListDTO> viewLists(){		
		return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
	}
	
	//delete selected to-do list
	public boolean deleteToDoListById(Long id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}
	
	//create a new to-do list
	public ToDoListDTO createToDoList(ToDoList todolist) {
		ToDoList saved = this.repo.save(todolist);
		return this.mapToDTO(saved);
	}
}

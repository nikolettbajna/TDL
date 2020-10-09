package com.qa.tdl_project.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.tdl_project.dto.TaskDTO;
import com.qa.tdl_project.exception.TaskNotFoundException;
import com.qa.tdl_project.presistence.domain.Task;
import com.qa.tdl_project.presistence.repo.TaskRepo;
import com.qa.tdl_project.utils.TDLBeanUtils;

@Service
public class TaskService {
	
	private TaskRepo repo;
	private ModelMapper mapper;
	
	@Autowired
	public TaskService(TaskRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}
	
	private TaskDTO mapToDTO(Task tsk) {
		return this.mapper.map(tsk, TaskDTO.class);
	}

	//view a specific task by id
	public TaskDTO viewTaskById(Long id) {
		Task view = this.repo.findById(id).orElseThrow(TaskNotFoundException::new);
		return this.mapToDTO(view);
	}
	
	//delete selected task
	public boolean deleteTaskById(Long id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}
	
	//update a task
	public TaskDTO updateTaskById(TaskDTO taskdto, Long id) {
		Task taskUpdate = this.repo.findById(id).orElseThrow(TaskNotFoundException::new);
		TDLBeanUtils.merge(taskdto, taskUpdate);
		return this.mapToDTO(this.repo.save(taskUpdate));
	}
	
	//create a task
	public TaskDTO createTask(Task task) {
		Task saved = this.repo.save(task);
		return this.mapToDTO(saved);
	}
	
}
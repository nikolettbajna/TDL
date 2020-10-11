package com.qa.tdl_project.presistence.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "task")
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Task {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Size(min = 1, max = 120)
	private String name;
	
	@NotNull
	@Size(min = 1, max = 250)
	private String description;
	
	@NotNull
	@Size(min = 6, max = 11)
	private String importance;
	
	private Boolean isDone;
	
	@ManyToOne
	private ToDoList list;
	
	public Task(String name, String description, String importance, Boolean isDone, ToDoList list) {
		super();
		this.name = name;
		this.description = description;
		this.importance = importance;
		this.isDone = isDone;
		this.list = list;
	}
	
	public Task(String name, String description, String importance, Boolean isDone) {
		this.name = name;
		this.description = description;
		this.importance = importance;
		this.isDone = isDone;
	}

}

package com.qa.tdl_project.presistence.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "toDoList")
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ToDoList {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	
	@OneToMany(mappedBy = "list", cascade = CascadeType.ALL)
	private List<Task> tasks;
	
	public ToDoList(String title) {
		super();
		this.title = title;
	}
	
}

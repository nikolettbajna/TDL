package com.qa.tdl_project.dto;

import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ToDoListDTO {
	
	private Long id;
	private String title;
	private List<TaskDTO> tasks;

}

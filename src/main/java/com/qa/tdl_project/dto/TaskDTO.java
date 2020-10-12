package com.qa.tdl_project.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class TaskDTO {
	
	private Long id;
	private String name;
	private String description;
	private String importance;
	private Boolean isDone;

}

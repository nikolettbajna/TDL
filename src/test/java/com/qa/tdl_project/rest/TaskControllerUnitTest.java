package com.qa.tdl_project.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.qa.tdl_project.dto.TaskDTO;
import com.qa.tdl_project.presistence.domain.Task;
import com.qa.tdl_project.services.TaskService;


@SpringBootTest
class TaskControllerUnitTest {

    @Autowired
    private TaskController controller;

    @MockBean
    private TaskService service;

    @Autowired
    private ModelMapper modelMapper;

    private TaskDTO mapToDTO(Task task) {
        return this.modelMapper.map(task, TaskDTO.class);
    }

    private List<Task> taskList;
    private Task testTask;
    private Task testTaskwithID;
    private TaskDTO taskDTO;

    private final Long id = 1L;
    private final String name = "Buy milk";
    private final String description = "1 pint, half-fat";
    private final String importance = "Very important";
    private final Boolean isDone = false;
    

    @BeforeEach
    void init() {
        this.taskList = new ArrayList<>();
        this.testTask = new Task(name, description, importance, isDone);
        this.testTaskwithID = new Task(testTask.getName(), testTask.getDescription(), testTask.getImportance(), testTask.getIsDone());
        this.testTaskwithID.setId(id);
        this.taskList.add(testTaskwithID);
        this.taskDTO = this.mapToDTO(testTaskwithID);
    }

    @Test
    void createTest() {
        when(this.service.createTask(testTask)).thenReturn(this.taskDTO);
        TaskDTO testCreated = this.taskDTO;
        assertThat(new ResponseEntity<TaskDTO>(testCreated, HttpStatus.CREATED))
                .isEqualTo(this.controller.create(testTask));
        verify(this.service, times(1)).createTask(this.testTask);
    }

    @Test
    void viewTest() {
        when(this.service.viewTaskById(this.id))
            .thenReturn(this.taskDTO);

        TaskDTO testReadOne = this.taskDTO;
        assertThat(new ResponseEntity<TaskDTO>(testReadOne, HttpStatus.OK))
                .isEqualTo(this.controller.viewTDLById(this.id));
        verify(this.service, times(1)).viewTaskById(this.id);
    }


    @Test
    void deleteTest() {
        this.controller.deleteTaskById(id);
        verify(this.service, times(1)).deleteTaskById(id);
    }

}
package com.qa.tdl_project.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.qa.tdl_project.dto.ToDoListDTO;
import com.qa.tdl_project.presistence.domain.ToDoList;
import com.qa.tdl_project.services.ToDoListService;

@SpringBootTest
class ToDoListControllerUnitTest {

    @Autowired
    private ToDoListController controller;

    @MockBean
    private ToDoListService service;

    @Autowired
    private ModelMapper modelMapper;

    private ToDoListDTO mapToDTO(ToDoList tdl) {
        return this.modelMapper.map(tdl, ToDoListDTO.class);
    }

    private List<ToDoList> tdlList;
    private ToDoList testTDL;
    private ToDoList testTDLwithID;
    private ToDoListDTO tdlDTO;

    private final Long id = 1L;
    private final String title = "Test list for Unit Testing";
    

    @BeforeEach
    void init() {
        this.tdlList = new ArrayList<>();
        this.testTDL = new ToDoList(title);
        this.testTDLwithID = new ToDoList(testTDL.getTitle());
        this.testTDLwithID.setId(id);
        this.tdlList.add(testTDLwithID);
        this.tdlDTO = this.mapToDTO(testTDLwithID);
    }

    @Test
    void createTest() {
        when(this.service.createToDoList(testTDL)).thenReturn(this.tdlDTO);
        ToDoListDTO testCreated = this.tdlDTO;
        assertThat(new ResponseEntity<ToDoListDTO>(testCreated, HttpStatus.CREATED))
                .isEqualTo(this.controller.create(testTDL));
        verify(this.service, times(1)).createToDoList(this.testTDL);
    }

    @Test
    void readTest() {
        when(this.service.viewTDLById(this.id))
            .thenReturn(this.tdlDTO);

        ToDoListDTO testReadOne = this.tdlDTO;
        assertThat(new ResponseEntity<ToDoListDTO>(testReadOne, HttpStatus.OK))
                .isEqualTo(this.controller.viewTDLById(this.id));
        verify(this.service, times(1)).viewTDLById(this.id);
    }

    @Test
    void readAllTest() {
        when(this.service.viewLists())
                .thenReturn(this.tdlList.stream().map(this::mapToDTO).collect(Collectors.toList()));
        assertThat(this.controller.getAllToDoLists().getBody().isEmpty()).isFalse();

        verify(this.service, times(1)).viewLists();
    }

    @Test
    void deleteTest() {
        this.controller.deleteToDoListById(id);
        verify(this.service, times(1)).deleteToDoListById(id);
    }

}

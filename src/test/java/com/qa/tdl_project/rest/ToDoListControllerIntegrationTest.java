package com.qa.tdl_project.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.tdl_project.dto.TaskDTO;
import com.qa.tdl_project.dto.ToDoListDTO;
import com.qa.tdl_project.presistence.domain.ToDoList;
import com.qa.tdl_project.presistence.repo.ToDoListRepo;

@SpringBootTest
@AutoConfigureMockMvc
class ToDoListControllerIntegrationTest {

    @Autowired
    private MockMvc mock;

    @Autowired
    private ToDoListRepo repo;

    @Autowired
    private ObjectMapper objectMapper;

    private Long id;
    private ToDoList testTDL;
    private ToDoList testTDLwithID;
    private ToDoListDTO tdlDTO;

    @BeforeEach
    void init() {
        this.repo.deleteAll();
        this.testTDL = new ToDoList("My Test Todo List");
        this.testTDLwithID = this.repo.save(this.testTDL);
        this.id = this.testTDLwithID.getId();
        List<TaskDTO> tasks = new ArrayList<>();
        this.tdlDTO = new ToDoListDTO(this.id, testTDL.getTitle(), tasks);
    }

    @Test
    void testCreate() throws Exception {
        this.mock
            .perform(request(HttpMethod.POST, "/todolists/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(this.testTDL))
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated())
            .andExpect(content().json(this.objectMapper.writeValueAsString(this.testTDLwithID)));
    }

    @Test
    void testView() throws Exception {
        this.mock
            .perform(request(HttpMethod.GET, "/todolists/view/" + this.id)
            	.contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().json(this.objectMapper.writeValueAsString(this.tdlDTO)));
    }

    @Test
    void testViewAll() throws Exception {
        List<ToDoListDTO> tdlList = new ArrayList<>();
        tdlList.add(this.tdlDTO);
        this.mock.perform(request(HttpMethod.GET, "/todolists/viewAll")
        		.contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().json(this.objectMapper.writeValueAsString(tdlList)));
     }

    @Test
    void testUpdate() throws Exception {
    	List<TaskDTO> tasks = new ArrayList<>();
        ToDoListDTO aList = new ToDoListDTO(this.id, "Test List for update", tasks);
        this.mock.perform(request(HttpMethod.PUT, "/todolists/update/" + this.id)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(aList)))
            .andExpect(status().isAccepted())
            .andExpect(content().json(this.objectMapper.writeValueAsString(aList)));
    }
    
    @Test
    void testDelete() throws Exception {
        this.mock
            .perform(request(HttpMethod.DELETE, "/todolists/delete/" + this.id))
            .andExpect(status().isNoContent());
    }

}

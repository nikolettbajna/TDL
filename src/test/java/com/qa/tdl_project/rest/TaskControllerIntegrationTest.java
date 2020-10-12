package com.qa.tdl_project.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.qa.tdl_project.presistence.domain.Task;
import com.qa.tdl_project.presistence.repo.TaskRepo;

@SpringBootTest
@AutoConfigureMockMvc
class TaskControllerIntegrationTest {

    @Autowired
    private MockMvc mock;

    @Autowired
    private TaskRepo repo;

    @Autowired
    private ObjectMapper objectMapper;

    private Long id;
    private Task testTask;
    private Task testTaskwithID;
    private TaskDTO taskDTO;

    @BeforeEach
    void init() {
        this.repo.deleteAll();
        this.testTask = new Task("present for mum", "birthday gift, she loves flowers", "Important", false);
        this.testTaskwithID = this.repo.save(this.testTask);
        this.id = this.testTaskwithID.getId();
        this.taskDTO = new TaskDTO(this.id, testTask.getName(), testTask.getDescription(), testTask.getImportance(), testTask.getIsDone());
    }

    @Test
    void testCreate() throws Exception {
        this.mock
            .perform(request(HttpMethod.POST, "/tasks/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(this.testTask))
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated())
            .andExpect(content().json(this.objectMapper.writeValueAsString(this.taskDTO)));
    }

    @Test
    void testView() throws Exception {
        this.mock
            .perform(request(HttpMethod.GET, "/tasks/view/" + this.id)
            	.contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().json(this.objectMapper.writeValueAsString(this.taskDTO)));
    }

    @Test
    void testUpdate() throws Exception {
        TaskDTO aList = new TaskDTO(this.id, "book for Peter", "something about dinosaurs", "Important", true);
        this.mock.perform(request(HttpMethod.PUT, "/tasks/update/" + this.id)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(aList)))
            .andExpect(status().isAccepted())
            .andExpect(content().json(this.objectMapper.writeValueAsString(aList)));
    }
    
    @Test
    void testDelete() throws Exception {
        this.mock
            .perform(request(HttpMethod.DELETE, "/tasks/delete/" + this.id))
            .andExpect(status().isNoContent());
    }

}

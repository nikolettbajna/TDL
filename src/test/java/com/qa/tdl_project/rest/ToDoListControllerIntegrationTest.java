package com.qa.tdl_project.rest;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
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
    private ModelMapper modelMapper;

    @Autowired
    private ObjectMapper objectMapper;

    private Long id;
    private ToDoList testTDL;
    private ToDoList testTDLwithID;
    
    private ToDoListDTO mapToDTO(ToDoList tdl) {
        return this.modelMapper.map(tdl, ToDoListDTO.class);
    }

    @BeforeEach
    void init() {
        this.repo.deleteAll();
        this.testTDL = new ToDoList("My Test Todo List");
        this.testTDLwithID = this.repo.save(this.testTDL);
        this.id = this.testTDLwithID.getId();
    }

    @Test
    void testCreate() throws Exception {
        this.mock
            .perform(request(HttpMethod.POST, "/todolists/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(testTDL))
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated())
            .andExpect(content().json(this.objectMapper.writeValueAsString(testTDLwithID)));
    }

    @Test
    void testView() throws Exception {
        this.mock
            .perform(request(HttpMethod.GET, "/todolists/view/" + this.id)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().json(this.objectMapper.writeValueAsString(this.testTDL)));
    }

    @Test
    void testViewAll() throws Exception {
        List<ToDoList> tdlList = new ArrayList<>();
        tdlList.add(this.testTDLwithID);

        String content = this.mock
            .perform(request(HttpMethod.GET, "/todolists/viewAll")
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn().getResponse().getContentAsString();
        
        assertEquals(this.objectMapper.writeValueAsString(tdlList), content);
    }

    @Test
    void testUpdate() throws Exception {
        ToDoList newTDL = new ToDoList("Test List for update");
        ToDoList updated = new ToDoList(newTDL.getTitle());
        updated.setId(this.id);

        String result = this.mock
            .perform(request(HttpMethod.PUT, "/todolists/update/" + this.id)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(newTDL)))
            .andExpect(status().isAccepted())
            .andReturn().getResponse().getContentAsString();
        
        assertEquals(this.objectMapper.writeValueAsString(this.mapToDTO(updated)), result);
    }
    
    @Test
    void testDelete() throws Exception {
        this.mock
            .perform(request(HttpMethod.DELETE, "/todolists/delete/" + this.id))
            .andExpect(status().isNoContent());
    }

}

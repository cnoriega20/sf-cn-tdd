package com.springboot.tdd.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.tdd.domain.ToDo;
import com.springboot.tdd.services.ToDoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class ToDoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private ToDoService toDoService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getAllTodos() throws Exception {
        List<ToDo> toDoList = new ArrayList<>();
        toDoList.add(new ToDo(1L, "Start React Course", true));
        toDoList.add(new ToDo(2L, "Start TypeScript Course", true));
        toDoList.add(new ToDo(3L, "Fork Destructuring Monolith Project", true));

        when(toDoService.getAllTodos()).thenReturn(toDoList);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/todoService/todos")
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(jsonPath("$",hasSize(3))).andDo(print());
    }

    @Test
    void testSuccessTodoCreation() throws Exception {
        //Arrange
        ToDo todo1=  ToDo.builder()
                .text("Study procedure")
                .completed(true)
                .build();
        String toDoToJson = objectMapper.writeValueAsString(todo1);
        given(toDoService.save(any(ToDo.class))).willReturn(todo1);
        //(1L, "Start React Course", true)

        // Act
        ResultActions result = mockMvc.perform(post("/api/v1/todoService/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toDoToJson));
        //Assert
        result.andExpect(status().isCreated())
                .andExpect(jsonPath("$.text").value("Study procedure"))
                .andExpect(jsonPath("$.completed").value(true));

    }
}

package com.springboot.tdd.services;

import com.springboot.tdd.domain.ToDo;
import com.springboot.tdd.repositories.ToDoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@SpringBootTest
class ToDoServiceTest {

    @MockBean
    private ToDoRepository toDoRepository;

    @Autowired
    ToDoService toDoService;

    @Test
    void testGetAllTodos() {
        List<ToDo> toDos = new ArrayList<>();
        toDos.add(ToDo.builder()
                .id(1L)
                .text("Continue with Steven's TypeScript course")
                .completed(true)
                .build());

        toDos.add(ToDo.builder()
                .id(2L)
                .text("Continue with Steven's TypeScript course")
                .completed(true)
                .build());


        given(toDoRepository.findAll()).willReturn(toDos);

        List<ToDo> otherTodos = toDoService.getAllTodos();

        assertEquals(otherTodos.size(), toDos.size());
        assertSame(otherTodos, toDos);


    }
}
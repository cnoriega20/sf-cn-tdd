package com.springboot.tdd.services;

import com.springboot.tdd.domain.ToDo;
import com.springboot.tdd.repositories.ToDoRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@Slf4j
class ToDoServiceTest {

    @Autowired
    //@MockBean
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

        //toDoRepository.saveAll(todos);


        given(toDoRepository.findAll()).willReturn(toDos);

        List<ToDo> otherTodos = toDoService.getAllTodos();

        assertEquals(otherTodos.size(), toDos.size());
        assertSame(otherTodos, toDos);
    }

    /*@Test
    void testGetAllTodoWithData(){
         ToDo firstTodo = ToDo.builder()
                .id(1L)
                .text("Continue with Steven's TypeScript course")
                .completed(true)
                .build();
        toDoRepository.save(firstTodo);

        ToDo firstExpectedTodo = toDoService.getAllTodos().get(0);

        assertEquals(firstTodo.getText(), firstExpectedTodo.getText());
        assertEquals(firstTodo.isCompleted(), firstExpectedTodo.isCompleted());
        assertEquals(firstTodo.getId(), firstExpectedTodo.getId());
    }*/

    /**
     * Change: mark TodoRepository to @Autowire
     * */
    @Test
    void testGetAllTodosWithData(){
        List<ToDo> toDos = new ArrayList<>();
        toDos.add(ToDo.builder()
                .id(1L)
                .text("Continue with Steven's TypeScript course")
                .completed(true)
                .build());

        toDos.add(ToDo.builder()
                .id(2L)
                .text("Start Pluralsight Angular course")
                .completed(true)
                .build());

        List<ToDo> repList = toDoRepository.saveAll(toDos);

        log.info("Rep list: " + repList);

        List<ToDo> serviceList = toDoService.getAllTodos();

        log.info("Serv list: " +  serviceList);

        ToDo firstElement = toDoService.getAllTodos().get(0);

        assertEquals(toDos.get(0).getText(), firstElement.getText());
        assertEquals(toDos.get(0).isCompleted(),firstElement.isCompleted());
        assertEquals(toDos.get(0).getId(),firstElement.getId());


    }
}
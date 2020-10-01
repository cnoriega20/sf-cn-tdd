package com.springboot.tdd.controllers;

import com.springboot.tdd.domain.ToDo;
import com.springboot.tdd.services.ToDoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/todoService/")
public class ToDoController {

    private final ToDoService toDoService;

    @GetMapping("/todos")
    ResponseEntity<List<ToDo>> getAllTodoItems(){
        return new ResponseEntity<>(toDoService.getAllTodos(), HttpStatus.OK);
    }

    @PostMapping("/todos")
    ResponseEntity<ToDo> createToDo(@RequestBody ToDo todo){
        return new ResponseEntity<>(toDoService.save(todo), HttpStatus.CREATED);
    }
}

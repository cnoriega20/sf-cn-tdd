package com.springboot.tdd.services;

import com.springboot.tdd.domain.ToDo;
import com.springboot.tdd.repositories.ToDoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDoServiceImpl implements ToDoService {

    private final ToDoRepository toDoRepository;

    public ToDoServiceImpl(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    @Override
    public List<ToDo> getAllTodos() {
        return toDoRepository.findAll();
    }
}

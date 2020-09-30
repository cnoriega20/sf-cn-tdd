package com.springboot.tdd.services;

import com.springboot.tdd.domain.ToDo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ToDoServiceImpl implements ToDoService {

    @Override
    public List<ToDo> getAllTodos() {
        return new ArrayList<>();
    }
}

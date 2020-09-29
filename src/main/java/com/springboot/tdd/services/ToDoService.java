package com.springboot.tdd.services;

import com.springboot.tdd.domain.ToDo;

import java.util.List;

public interface ToDoService {

    List<ToDo> findAll();
}

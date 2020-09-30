package com.springboot.tdd.repositories;

import com.springboot.tdd.domain.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoRepository extends JpaRepository<ToDo, Long> {
}

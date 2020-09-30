package com.springboot.tdd.loaders;

import com.springboot.tdd.domain.ToDo;
import com.springboot.tdd.repositories.ToDoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class ToDoLoader implements CommandLineRunner {

    private final ToDoRepository toDoRepository;

    @Override
    public void run(String... args) throws Exception {
        List<ToDo> toDos = new ArrayList<>();
        toDos.add(ToDo.builder()
                .id(1L)
                .text("Continue with Steven's TypeScript course")
                .completed(true)
                .build());

        toDos.add(ToDo.builder()
                .id(2L)
                .text("Start with Pluralsight Angular course")
                .completed(true)
                .build());

        toDoRepository.saveAll(toDos);

        log.info("Loaded ToDOs: " + toDoRepository.count());
    }
}

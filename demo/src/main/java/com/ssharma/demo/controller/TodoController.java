package com.ssharma.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssharma.demo.TodoElasticSearch;
import com.ssharma.demo.TodoEntity;
import com.ssharma.demo.service.TodoService;

@RestController
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @PostMapping
    public TodoEntity createTodo(@RequestBody TodoEntity todoEntity) {
        return todoService.createTodo(todoEntity);
    }

    @GetMapping("/search/{id}")
    public Optional<TodoElasticSearch> searchTodoInElasticsearch(@PathVariable String id) {
        return todoService.searchTodoInElasticsearch(id);
    }

    @GetMapping("/cache/{id}")
    public TodoEntity getTodoFromCache(@PathVariable Long id) {
        return todoService.getTodoFromCache(id);
    }

    @GetMapping("/queue/pop")
    public TodoEntity popTodoFromQueue() {
        return todoService.popTodoFromQueue();
    }
}

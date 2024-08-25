package com.ssharma.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssharma.demo.TodoElasticSearch;
import com.ssharma.demo.TodoEntity;
import com.ssharma.demo.repository.TodoElasticsearchRepository;
import com.ssharma.demo.repository.TodoRepository;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private TodoElasticsearchRepository todoElasticsearchRepository;

    @Autowired
    private TodoQueueService todoQueueService;

    @Autowired
    private TodoCacheService todoCacheService;

    @Transactional
    public TodoEntity createTodo(TodoEntity todoEntity) {
        // Save in Oracle
        TodoEntity savedEntity = todoRepository.save(todoEntity);

        // Index in Elasticsearch
        TodoElasticSearch elasticsearchDocument = new TodoElasticSearch();
        elasticsearchDocument.setId(savedEntity.getId().toString());
        elasticsearchDocument.setTitle(savedEntity.getTitle());
        elasticsearchDocument.setDescription(savedEntity.getDescription());
        elasticsearchDocument.setCompleted(savedEntity.isCompleted());
        todoElasticsearchRepository.save(elasticsearchDocument);

        // Cache the Todo
        todoCacheService.cacheTodo(savedEntity.getId(), savedEntity);

        // Push to Redis Queue
        todoQueueService.pushToQueue(savedEntity);

        return savedEntity;
    }

    public Optional<TodoElasticSearch> searchTodoInElasticsearch(String id) {
        return todoElasticsearchRepository.findById(id);
    }

    public Iterable<TodoElasticSearch> searchTodoInElasticsearch() {
        return todoElasticsearchRepository.findAll();
    }

    public TodoEntity getTodoFromCache(Long id) {
        return todoCacheService.getCachedTodo(id);
    }

    public TodoEntity popTodoFromQueue() {
        return todoQueueService.popFromQueue();
    }
}

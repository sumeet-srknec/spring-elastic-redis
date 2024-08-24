package com.ssharma.demo.service;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.ssharma.demo.TodoEntity;

@Service
public class TodoCacheService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final String TODO_CACHE_PREFIX = "todo:";

    public void cacheTodo(Long id, TodoEntity todoEntity) {
        redisTemplate.opsForValue().set(TODO_CACHE_PREFIX + id, todoEntity, 10, TimeUnit.MINUTES);
    }

    public TodoEntity getCachedTodo(Long id) {
        return (TodoEntity) redisTemplate.opsForValue().get(TODO_CACHE_PREFIX + id);
    }

    public void invalidateCache(Long id) {
        redisTemplate.delete(TODO_CACHE_PREFIX + id);
    }
}

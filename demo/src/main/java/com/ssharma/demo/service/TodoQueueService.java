package com.ssharma.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.ssharma.demo.TodoEntity;

@Service
public class TodoQueueService {

    private static final String TODO_QUEUE = "todoQueue";

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void pushToQueue(TodoEntity todoEntity) {
        redisTemplate.opsForList().leftPush(TODO_QUEUE, todoEntity);
    }

    public TodoEntity popFromQueue() {
        return (TodoEntity) redisTemplate.opsForList().rightPop(TODO_QUEUE);
    }
}

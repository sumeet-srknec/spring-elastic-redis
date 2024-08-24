package com.ssharma.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ssharma.demo.TodoEntity;

public interface TodoRepository extends JpaRepository<TodoEntity, Long> {
}

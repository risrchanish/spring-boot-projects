package com.risrchanish.todo.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.risrchanish.todo.todo.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long>{

}

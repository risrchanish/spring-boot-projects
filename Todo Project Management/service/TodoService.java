package com.risrchanish.todo.todo.service;



import java.util.List;

import com.risrchanish.todo.todo.dto.TodoDto;

public interface TodoService {

	public TodoDto addTodo(TodoDto todo); 
	public TodoDto getDto(Long id);
	public TodoDto updateDto(TodoDto dto, Long id);
	public void deleteTodo(Long id);
	public TodoDto completeTodo(Long id);
	public TodoDto inCompleteTodo(Long id);
	public List<TodoDto> getAllTodoDto();
}

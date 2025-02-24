package com.risrchanish.todo.todo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.risrchanish.todo.todo.dto.TodoDto;
import com.risrchanish.todo.todo.entity.Todo;
import com.risrchanish.todo.todo.exception.ResourceNotFoundException;
import com.risrchanish.todo.todo.repository.TodoRepository;
import com.risrchanish.todo.todo.service.TodoService;

@Service
public class TodoServiceImpl implements TodoService{
	
	private TodoRepository repository;
	private ModelMapper modelMapper;
	
	@Autowired
	public TodoServiceImpl(TodoRepository repository, ModelMapper modelMapper)
	{
		this.repository = repository;
		this.modelMapper = modelMapper;
	}

	@Override
	public TodoDto addTodo(TodoDto dto) {
		
		Todo save = modelMapper.map(dto, Todo.class);
		repository.save(save);
		TodoDto todto = modelMapper.map(save, TodoDto.class);
		return todto;
		
	}

	@Override
	public TodoDto getDto(Long id) {
		
		Todo toDo = repository.findById(id).get();
		return modelMapper.map(toDo, TodoDto.class);
	}

	@Override
	public TodoDto updateDto(TodoDto dto, Long id) {
		
		repository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Given Id is not present"));
		Todo todo = new Todo();
		todo.setId(dto.getId());
		todo.setTitle(dto.getTitle());
		todo.setDescription(dto.getDescription());
		todo.setCompleted(dto.isCompleted());	
		
		repository.save(todo);
		
		TodoDto todto = modelMapper.map(todo, TodoDto.class);
		return todto;
		
	}

	@Override
	public void deleteTodo(Long id) {
		repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("id is not present"));
		repository.deleteById(id);
		
	}

	@Override
	public TodoDto completeTodo(Long id) 
	{
		
		Todo todo = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("data not found with " +id));
		todo.setCompleted(Boolean.TRUE);
		repository.save(todo);
		
		return modelMapper.map(todo, TodoDto.class);
	}

	@Override
	public TodoDto inCompleteTodo(Long id) 
	{
		
		Todo todo = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("data not found with "+ id));
		todo.setCompleted(Boolean.FALSE);
		Todo updatedValue = repository.save(todo);
		
		return modelMapper.map(updatedValue, TodoDto.class);
		
	}

	@Override
	public List<TodoDto> getAllTodoDto() {
		
		List<Todo> todoList = repository.findAll();
		List<TodoDto> dtoList = new ArrayList<>();
		
		todoList.forEach((todo) -> {
			
			TodoDto dto = modelMapper.map(todo, TodoDto.class);
			dtoList.add(dto);
			
		});
		
		return dtoList;
	}

}

package com.risrchanish.todo.todo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.risrchanish.todo.todo.dto.TodoDto;
import com.risrchanish.todo.todo.entity.Todo;
import com.risrchanish.todo.todo.service.TodoService;

@RestController
@RequestMapping("todo")
public class TodoController {

	private TodoService todoService;
	
	public TodoController(TodoService todoService)
	{
		this.todoService = todoService;
	}
	
	
	
	@PreAuthorize("hasRole('ADMIN')")  // method level authorization
	@PostMapping("save")  // http://localhost:8080/todo/save
	public ResponseEntity<TodoDto> addTodoDtoMethod(@RequestBody TodoDto dto)
	{
		TodoDto todoDto = todoService.addTodo(dto);
		return new ResponseEntity<>(todoDto,HttpStatus.CREATED);
	}
	
	
	
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@GetMapping("{id}") // http://localhost:8080/todo/1
	public ResponseEntity<TodoDto> findTodoById(@PathVariable Long id)
	{
		TodoDto dto =  todoService.getDto(id);
		
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("update/{id}")  // http://localhost:8080/todo/update/3
	public ResponseEntity<TodoDto> updateDtoData(@RequestBody TodoDto dto , @PathVariable Long id)
	{
		TodoDto toDto = todoService.updateDto(dto, id);
		
		return new ResponseEntity<>(toDto, HttpStatus.OK);
	}
	
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("deleted/{id}")	// http://localhost:8080/todo/deleted/1
	public ResponseEntity<String> deleteByIdMethod(@PathVariable Long id)
	{
		todoService.deleteTodo(id);
		
		return ResponseEntity.ok("Details has been deleted");
	}
	
	// Patch mapping to update any particular record.
	
	
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@PatchMapping("{id}/completed")  // http://localhost:8080/todo/2/completed
	public ResponseEntity<TodoDto> completeTodoMethod(@PathVariable Long id)
	{
		TodoDto dto = todoService.completeTodo(id);
		return ResponseEntity.ok(dto);
	}
	
	
	// Patch mapping to update any particular incomplete record.
	
	
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@PatchMapping("{id}/in-complete")   // http://localhost:8080/todo/3/in-complete
	public ResponseEntity<TodoDto> inCompleteTodoDto(@PathVariable Long id)
	{
		TodoDto dto = todoService.inCompleteTodo(id);
		return ResponseEntity.ok(dto);
	}
	
	
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@GetMapping("todos") // http://localhost:8080/todo/todos
	public ResponseEntity<List<TodoDto>> getAllTodoDtoMethod()
	{
		List<TodoDto> fetchAll =  todoService.getAllTodoDto();
		
		return new ResponseEntity<>(fetchAll, HttpStatus.OK);
	}
}

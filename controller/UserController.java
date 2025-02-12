package net.javaguides.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springboot.entity.User;
import net.javaguides.springboot.service.UserService;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {

	@Autowired
	private UserService userService;
	
//	public UserController(UserService userService)
//	{
//		this.userService = userService;
//	}
	
	
	// build user REST API
	
	@PostMapping  // http://localhost:8080/api/users
	public ResponseEntity<User> createUser(@RequestBody User user)
	{
		User savedUser = userService.createUser(user);
		
		return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
	}
	
	@GetMapping("{id}")  // http://localhost:8080/api/users/1
	public ResponseEntity<User> findUserById(@PathVariable Long id)
	{
		User getUser = userService.findUserById(id);
		return new ResponseEntity<>(getUser, HttpStatus.OK);
	}
	
	
	@GetMapping("list")    // http://localhost:8080/api/users/list
	public ResponseEntity<List<User>> findAllUsers()
	{
		List<User> userList = userService.findAllUsers();
		userList.forEach(u ->{
			System.out.println(u);
		});
		
		return new ResponseEntity<>(userList,HttpStatus.OK);
	}
	
	
	@PutMapping("update/{id}")  //  http://localhost:8080/api/users/update
	public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable Long id)
	{
		user.setId(id);
		User updatedUser = userService.updateUser(user);
		
		return new ResponseEntity<>(updatedUser,HttpStatus.OK);
	}
	
	
	@DeleteMapping("delete/{id}")  //http://localhost:8080/api/users/delete/1
	public ResponseEntity<String> deleteUserById(@PathVariable Long id)
	{
		userService.deleteUser(id);
		return new ResponseEntity<>("User deleted",HttpStatus.OK);
	}
}

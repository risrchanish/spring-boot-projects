package net.javaguides.springboot.service;


import net.javaguides.springboot.entity.User;
import java.util.List;


public interface UserService {

	public User createUser(User user);
	public User findUserById(long id);
	public List<User> findAllUsers();
	public User updateUser(User user);
	public void deleteUser(Long id);
	
	
}

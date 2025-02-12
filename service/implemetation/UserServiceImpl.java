package net.javaguides.springboot.service.implemetation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.entity.User;
import net.javaguides.springboot.repository.UserRepository;
import net.javaguides.springboot.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository repository;
	
	
//	public UserServiceImpl(UserRepository repository)
//	{
//		this.repository = repository;
//	}
	
	@Override
	public User createUser(User user) {
		
		return repository.save(user);
	}


	@Override
	public User findUserById(long id) {
		
		return repository.findById(id).get();
	}


	@Override
	public List<User> findAllUsers() {
		
		return repository.findAll();
	}


	@Override
	public User updateUser(User user) {
		
		User existingUser = repository.findById(user.getId()).get();
		
		existingUser.setEmail(user.getEmail());
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		
		User updatedUser = repository.save(existingUser);
		
		return updatedUser;
		
	}


	@Override
	public void deleteUser(Long id) {
		if(id != null)
		{
		repository.deleteById(id);
		}
		else
		{
			System.out.println("Id not found");
		}
		
	}

}

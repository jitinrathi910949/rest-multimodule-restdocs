package org.sample.multimodule.service.impl;

import java.util.List;



import org.sample.multimodule.model.User;
import org.sample.multimodule.repository.UserRepository;
import org.sample.multimodule.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Hello world!
 *
 */
@Service
public class UserServiceImpl implements UserService
{
	
	@Autowired
	private UserRepository repository;

	@Override
	public List<User> allUsers() {
		
		return (List<User>) repository.findAll();
	}

	@Override
	public User createUser(User user) {
		// TODO Auto-generated method stub
		return repository.save(user);
	}

	@Override
	public User getUserById(Long Id) {
		// TODO Auto-generated method stub
		return repository.findOne(Id);
	}

	@Override
	public String deleteUser(Long Id) {
		// TODO Auto-generated method stub
		repository.delete(Id);
		return "User with Id="+Id+" deleted successfully";
	}

	@Override
	public User updateUser(Long Id, String name) {
		User user=new User();
		user=repository.findOne(Id);
		user.setName(name);
		
		return user;
	}
   
}

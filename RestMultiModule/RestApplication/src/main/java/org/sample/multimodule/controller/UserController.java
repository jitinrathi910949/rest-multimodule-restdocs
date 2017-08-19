package org.sample.multimodule.controller;

import java.util.Arrays;
import java.util.List;


import org.apache.log4j.Logger;
import org.sample.multimodule.model.User;
import org.sample.multimodule.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
	final static Logger logger = Logger.getLogger(UserController.class);
	
	
	@Autowired
	private UserService userService;
	@GetMapping("/")
	public String WelcomePage() {
		return "WelcomeUser";
	}
	
	//Adds the user
	@RequestMapping(value="/adduser", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	
	public ResponseEntity<User> addUser(@RequestBody User user){
		userService.createUser(user);
		
		logger.debug("Added:: " + user);
		return new ResponseEntity<User>(user,HttpStatus.CREATED);
	}
	
	
	//List all the Users
	@RequestMapping(value="/alluser", method=RequestMethod.GET)
	public ResponseEntity<List<User>> getAllUser(){
		List<User> users= userService.allUsers();
		if(users.isEmpty()) {
			logger.debug("users does not exist");
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
		}else {
		logger.debug("users found!!!");
		logger.debug(Arrays.toString(users.toArray()));
		return new ResponseEntity<List<User>>(users,HttpStatus.OK);
		}	}
	
	//Deletes the users
	@RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<String> deleteUser(@PathVariable("id") Long Id){
		List<User> users= userService.allUsers();
		if(users.isEmpty()) {
			logger.debug("users does not exist");
			String message="User does not exist";
			return new ResponseEntity<String>(message,HttpStatus.NO_CONTENT);
		}else {
			userService.deleteUser(Id);
			logger.debug("user with id="+Id+" deleted successfully");
			String message="User with id="+Id+" deleted successfully";
			return new ResponseEntity<String>(message,HttpStatus.OK);
		}}
		
		
		//Gets Users by id
		@RequestMapping(value="/{id}", method=RequestMethod.GET)
		public ResponseEntity<User> getUserById(@PathVariable("id")Long Id){
			User user= userService.getUserById(Id);
			if(user==null) {
				logger.debug("user not present");
				return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
			}else {
				logger.debug("Found user");
				return new ResponseEntity<User>(user,HttpStatus.OK); 
			}
		}
		 
		
	}


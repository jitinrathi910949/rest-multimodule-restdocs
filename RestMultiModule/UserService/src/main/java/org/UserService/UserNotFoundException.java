package org.UserService;

public class UserNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UserNotFoundException(Long Id) {
		super("No such User found with Id"+Id);
	}

}

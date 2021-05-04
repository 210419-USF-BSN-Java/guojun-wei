package com.revature.services;

import com.revature.models.User;

public interface UserService {

	User registration(User user);

	User verifyUser(String userName, String password);
	
	User addEmployee(User user);
	
}

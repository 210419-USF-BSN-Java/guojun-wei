package com.revature.DAOs;

import com.revature.models.User;

public interface UserDAO extends GenericDAO<User>{

	Boolean deleteEmployee(Integer employeeID);
	
	public User add(User u);
	
	public User getByUserName(String userName);
	
	public User addEmployee(User u);
}

package com.revature.daos;


import java.util.List;

import com.revature.models.User;

public interface UserDao {
	
	User getUserInfo(String userName);

	Boolean updateUserInfo(String userName, String password, String firstName, String lastName, String email, Integer userId) throws Exception;
	
	List<User> getAllEmployee(Integer userRoleId) throws Exception;
	
	List<User> getAll() throws Exception;

	User getUserByUsernameAndPassword(String username, String password) throws Exception;

	User getByUserId(int userId) throws Exception; 
}


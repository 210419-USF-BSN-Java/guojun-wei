package com.revature.services;

import static org.junit.Assert.*;

import org.junit.Test;

import com.revature.models.User;

public class UserServiceImplementationTest {

	@Test
	public void test() {
		//fail("Not yet implemented");
		
	}
	
	@Test
	public void testVerifyUser() {
		UserServiceImplementation user = new UserServiceImplementation();
		User user2 = new User(8, "John", "Smith", "luffy", "456", 2);
		assertEquals(user2, user.verifyUser("luffy", "456"));
	}

	@Test 
	public void testRegistration() {
		User u = new User(null, "John", "Doe", "employeeOne", "password", 2);
		UserServiceImplementation user = new UserServiceImplementation();
		user.registration(u);
	}
	
}

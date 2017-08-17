package org.revolute.service;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.revolute.domain.User;

public class UserServiceTest {

	UserService userService ;
	
	@Before
	public  void beforeTest() throws Exception {
		userService = new UserService();
	}

	@Test
	public void UserService_NotNulltest() {
		assertNotNull(userService);
	}

	@Test
	public void CreatingAnding1User_ExpectedNumberOfUsers1() {
		userService.addUser(userService.createUser("biniam" , "London"));
		assertEquals(1, userService.getAllUsers().size());
	}
	
	@Test
	public void UpdatingUserTest_ExpectedEquallUserData() {
		User user = userService.createUser("biniam", "London");
		userService.addUser(user);
		user = userService.updateUser(user.getId(), "daniel", "Newyork");
		
		assertEquals("daniel", user.getName());
		assertEquals("Newyork", user.getAddress());
	}
	
	
}

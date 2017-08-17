package org.revolute.service;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.revolute.domain.Account;
import org.revolute.domain.AccountType;
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
	
	@Test
	public void Add2UserWith2AccountEach_ExpectedNumberOfAccounts4() {
		User biniam = userService.createUser("biniam" , "London");
		biniam.addAccount(AccountType.SAVING,100000.00);
		biniam.addAccount(AccountType.CLASSIC,1000.00);
		userService.addUser(biniam);
		
		User poorDaniel = userService.createUser("daniel" , "NewYork");
		poorDaniel.addAccount(AccountType.SAVING,0.00);
		poorDaniel.addAccount(AccountType.CLASSIC,100.00);
		userService.addUser(poorDaniel);
		
		assertEquals(4,userService.getAllUsersAccounts().size());
	}
	
	@Test
	public void getAccount_ExpectedBiniamSavingAccount() {
		
		User biniam = userService.createUser("biniam" , "London");
		Account biniamSavingAccount = biniam.addAccount(AccountType.SAVING,100000.00);
		biniam.addAccount(AccountType.CLASSIC,1000.00);
		userService.addUser(biniam);
		
		User poorDaniel = userService.createUser("daniel" , "NewYork");
		poorDaniel.addAccount(AccountType.SAVING,0.00);
		poorDaniel.addAccount(AccountType.CLASSIC,100.00);
		userService.addUser(poorDaniel);
		
		assertEquals(biniamSavingAccount,userService.getAccount(biniam.getId() + "SavingAccount"));
	}
	
	
}

package org.revolute.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class userTest {

	String name = "biniam";
	String id = "biniamId";
	String address = "london";
	
	User biniam;
	
	@Before
	public void beforeTest() {
		biniam = new User(id, name, address);
	}
	
	@Test
	public void userConstructorTest() {
		String expected = "user [Id=" + id + ", Name=" + name + ", Address=" + address + "]";
		assertEquals(expected, biniam.toString());
		
	}
	
	@Test
	public void Add1AccountToUser_ExpectedNumberOfUserAccount1() {
		biniam.addAccount(AccountType.CLASSIC, 100);
		assertEquals(1,biniam.getAccounts().size());
	}
	
	@Test
	public void Add2AccountToUser_ExpectedNumberOfUserAccount2() {
		biniam.addAccount(AccountType.CLASSIC, 100);
		biniam.addAccount(AccountType.SAVING, 100);
		assertEquals(2,biniam.getAccounts().size());
	}
	
	
}

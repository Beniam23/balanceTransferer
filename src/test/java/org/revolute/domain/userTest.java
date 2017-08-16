package org.revolute.domain;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class userTest {

	@Test
	public void userConstructorTest() {
		String name = "biniam";
		String id = "biniamId";
		String address = "london";
		
		User user = new User(id, name, address);
		String expected = "user [Id=" + id + ", Name=" + name + ", Address=" + address + "]";
		assertEquals(expected, user.toString());
		
	}
	
}

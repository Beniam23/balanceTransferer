package org.revolute.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.revolute.exception.ActionProhibitedException;
import org.revolute.exception.InsufficientBalanceException;

public class AccountTest {
	
	Account classicAccount ;
	Account savingAccount ;
	Account classicAccount2;

	@Before
	public void beforeTest(){
		classicAccount = new ClassicAccount("classicAccount1", 100);
		savingAccount = new SavingAccount("savingAccount1", 100);
	}
	
	@Test
	public void Depositing100_ExpectedBalance200(){
		// classic Account
		classicAccount.deposit(100);
		assertEquals(200.00,((ClassicAccount) classicAccount).getBalance(),0.00);
		
		// saving Account
		savingAccount.deposit(100);
		assertEquals(200.00,((SavingAccount) savingAccount).getBalance(),0.00);
	}

	
	@Test(expected = ActionProhibitedException.class)
	public void WithDrawing50FromSavingAccount_ExpectedToThrowException() throws Exception {
		savingAccount.withdraw(50);
	}
	
	
	@Test
	public void Withdrawing50FromClassicAccount_ExpectedBalance50() {
		try {
			classicAccount.withdraw(50);
			assertEquals(50,((ClassicAccount) classicAccount).getBalance(),0.00);
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	
	@Test
	public void Withdrawing100FromClassicAccount_ExpectedBalance0() {
		try {
			classicAccount.withdraw(100);
			assertEquals(0,((ClassicAccount) classicAccount).getBalance(),0.00);
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
		
	}
	
	
	@Test(expected = InsufficientBalanceException.class)
	public void Withdrawing110FromClassicAccount_ExpectedToThrowException() throws Exception {
			classicAccount.withdraw(110);
			assertEquals(0,((ClassicAccount) classicAccount).getBalance(),0.00);	
	}
	 
	@Test
	public void Transfer100ToSavingAccountFromClassicAccount_ExpectedSavingAccountBalance200ClassicAccount0() {
			try {
				classicAccount.transfer(100, savingAccount);
				assertEquals(200.00,((SavingAccount) savingAccount).getBalance(),0.00);
				assertEquals(0,((ClassicAccount) classicAccount).getBalance(),0.00);
			} catch (Exception e) {
				fail(e.getMessage());
			}
	}
	
	@Test
	public void Transfer100ToClassicAccountFromClassicAccount2_ExpectedClassicAccountBalance200ClassicAccount20() {
		classicAccount2 = new ClassicAccount("classicAccount2", 100);
		try {
			classicAccount2.transfer(100, classicAccount);
			assertEquals(200.00,((ClassicAccount) classicAccount).getBalance(),0.00);
			assertEquals(0,((ClassicAccount) classicAccount2).getBalance(),0.00);
		} catch (Exception e) {
			fail(e.getMessage());
		}
		
	}
	
	@Test(expected = ActionProhibitedException.class)
	public void Transfer100ToClassicAccountFromSavingAccount_ExpectedToThrowException() throws Exception {
			savingAccount.transfer(100, classicAccount);
	}
	
}

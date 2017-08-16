package org.revolute.domain;

import org.revolute.exception.InsufficientBalanceException;

public class ClassicAccount implements Account{

	 private String AccountId;
	 private double balance;
	
	public ClassicAccount(String accountId, double balance) {
		super();
		AccountId = accountId;
		this.balance = balance;
	}	

	/**
	 * @return the accountId
	 */
	public String getAccountId() {
		return AccountId;
	}

	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountId(String accountId) {
		AccountId = accountId;
	}
	
	/**
	 * @return the balance
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}

	public void deposit(double amount) {
		this.balance +=amount;
	}

	public void withdraw(double amount) throws InsufficientBalanceException{
		
		if(this.balance >= amount)
			this.balance -=amount;
		else
			throw new InsufficientBalanceException();
		
	}

	public void transfer(double amount, Account account) throws InsufficientBalanceException{
		withdraw(amount);
		account.deposit(amount);
	}

}

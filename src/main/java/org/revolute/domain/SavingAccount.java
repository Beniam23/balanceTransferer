package org.revolute.domain;

import org.revolute.exception.ActionProhabitedException;

public class SavingAccount implements Account{

	 private String AccountId;
	 private double balance;
	
	public SavingAccount(String accountId, double balance) {
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

	public void withdraw(double amount) throws ActionProhabitedException{
		throw new ActionProhabitedException();
	}

	public void transfer(double amount, Account account) throws ActionProhabitedException{
		withdraw(amount);
		account.deposit(amount);
	}

}


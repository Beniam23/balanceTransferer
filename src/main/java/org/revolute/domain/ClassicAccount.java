package org.revolute.domain;

import java.time.LocalDateTime;
import org.revolute.exception.InsufficientBalanceException;

/**
 * @author BINIAM GEBREYESUS	
 * @since 16/08/17 
 * @version 1.0 
 * */
public class ClassicAccount implements Account{

	private String AccountId;
	private volatile double balance;
	private LocalDateTime lastModified;
	
	public ClassicAccount(String accountId, double balance, LocalDateTime lastModified) {
		super();
		AccountId = accountId;
		this.balance = balance;
		this.lastModified = lastModified;
	}	

	/**
	 * @return the accountId
	 */
	public String getId() {
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
	 * @return the lastModified
	 */
	public LocalDateTime getLastModified() {
		return lastModified;
	}

	public void deposit(double amount) {
		this.balance +=amount;
		this.lastModified = LocalDateTime.now();
	}

	public synchronized void withdraw(double amount) throws InsufficientBalanceException{
		
		if(this.balance >= amount) {
			this.balance -=amount;
			this.lastModified = LocalDateTime.now();
		}else
			throw new InsufficientBalanceException();
		
	}

	public void transfer(double amount, Account account) throws InsufficientBalanceException{
		withdraw(amount);
		account.deposit(amount);
	}

}

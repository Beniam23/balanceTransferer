package org.revolute.domain;

/**
 * @author BINIAM GEBREYESUS	
 * @since 16/08/17 
 * @version 1.0 
 * */
public interface Account {
	
	public String getId();
	
	/**
	 * @param double amount 
	 * */
	public void deposit(double amount);
	
	/**
	 * @param double amount
	 * @throws InsufficientBalanceException 
	 * */
	public void withdraw(double amount) throws Exception;
	
	/**
	 * @param double amount
	 * @param Object Account 
	 * @throws InsufficientBalanceException and ActionProhibitedException
	 * */
	public void transfer(double amount, Account account) throws Exception;

}

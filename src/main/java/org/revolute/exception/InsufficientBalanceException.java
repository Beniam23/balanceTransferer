package org.revolute.exception;

public class InsufficientBalanceException extends Exception {

	private static final long serialVersionUID = 1L;
	private static final String MESSAGE = "InsufficientBalanceException: Insufficient Balance found !!"; 
	
	@Override
	public String getMessage() {
		return MESSAGE ;
	}
	
}

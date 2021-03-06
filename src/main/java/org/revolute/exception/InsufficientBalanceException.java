package org.revolute.exception;

/**
 * @author BINIAM GEBREYESUS	
 * @since 17/08/17 
 * @version 1.0 
 * */
public class InsufficientBalanceException extends Exception {

	private static final long serialVersionUID = 1L;
	private static final String MESSAGE = "InsufficientBalanceException: Insufficient Balance found !!"; 
	
	@Override
	public String getMessage() {
		return MESSAGE ;
	}
	
}

package org.revolute.exception;

public class ActionProhabitedException extends Exception {

	private static final long serialVersionUID = 1L;
	private static final String MESSAGE = "ActionProhabitedException : This Action is not permitted !!"; 
	
	@Override
	public String getMessage() {
		return MESSAGE ;
	}
	
}

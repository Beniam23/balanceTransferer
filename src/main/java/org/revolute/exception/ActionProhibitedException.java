package org.revolute.exception;

/**
 * @author BINIAM GEBREYESUS	
 * @since 17/08/17 
 * @version 1.0 
 * */
public class ActionProhibitedException extends Exception {

	private static final long serialVersionUID = 1L;
	private static final String MESSAGE = "ActionProhibitedException : This Action is not permitted !!"; 
	
	@Override
	public String getMessage() {
		return MESSAGE ;
	}
	
}

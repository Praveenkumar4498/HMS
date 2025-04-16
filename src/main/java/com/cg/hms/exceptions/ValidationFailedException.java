package com.cg.hms.exceptions;


public class ValidationFailedException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ValidationFailedException() {
		
	}
	public ValidationFailedException(String msg) {
		super(msg);
	}
	

}

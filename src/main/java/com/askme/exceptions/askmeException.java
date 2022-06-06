package com.askme.exceptions;

public class askmeException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 661664845078728976L;

	public askmeException(String exMessage, Exception exception) {
		super(exMessage,exception);
	}
	
	public askmeException(String exMessage ) {
		super(exMessage);
	}
}

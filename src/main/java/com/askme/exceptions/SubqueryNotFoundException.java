package com.askme.exceptions;

public class SubqueryNotFoundException extends RuntimeException {
	
	 /**we implement seraliziable interface
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public SubqueryNotFoundException() {
        super();
	}

	public SubqueryNotFoundException(String message) {
	        super(message);
	}
}

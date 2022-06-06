package com.askme.dto;

public class MessagesResponse {
	
	private String message;

	public MessagesResponse(String message) {
	    this.message = message;
	  }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}

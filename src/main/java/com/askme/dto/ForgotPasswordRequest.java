package com.askme.dto;

public class ForgotPasswordRequest {
	  private String email;

	public ForgotPasswordRequest(String email) {
		super();
		this.email = email;
	}

	public ForgotPasswordRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	  

}

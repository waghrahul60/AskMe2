package com.askme.dto;



public class UserDto {
	
    private Long userId;
	
	private String firstName;
	
	
	private String lastName;
	
   
    private String username;
    
    
    
    private String email;
    
   
    private String whoAreYou;


	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}


	public UserDto(Long userId, String firstName, String lastName, String username, String email,
			String whoAreYou) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
	
		this.email = email;
		this.whoAreYou = whoAreYou;
	}


	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	

	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getWhoAreYou() {
		return whoAreYou;
	}


	public void setWhoAreYou(String whoAreYou) {
		this.whoAreYou = whoAreYou;
	}
    
    

}

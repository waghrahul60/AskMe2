package com.askme.model;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;


@Entity
public class User {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
	
	//@NotBlank(message = "First name is required")
	private String firstName;
	
	//@NotBlank(message = "Last Name is required")
	private String lastName;
	
    @NotBlank(message = "Username is required")
    private String username;
    
    @NotBlank(message = "Password is required")
    private String password;
    
    @Email
    @NotEmpty(message = "Email is required")
    private String email;
    
   // @NotBlank(message = "Role is required")
    private String whoAreYou;
    
    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinTable(	name = "user_roles", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();
    
    private Instant created;
    private boolean enabled;
    
    
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(Long userId, @NotBlank(message = "First name is required") String firstName,
			@NotBlank(message = "Last Name is required") String lastName,
			@NotBlank(message = "Username is required") String username,
			@NotBlank(message = "Password is required") String password,
			@Email @NotEmpty(message = "Email is required") String email,
			@NotBlank(message = "Role is required") String whoAreYou, Set<Role> roles, Instant created,
			boolean enabled) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.whoAreYou = whoAreYou;
		this.roles = roles;
		this.created = created;
		this.enabled = enabled;
	}
	public String getWhoAreYou() {
		return whoAreYou;
	}
	public void setWhoAreYou(String whoAreYou) {
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


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Set<Role> getRoles() {
		return roles;
	}


	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}


	public Instant getCreated() {
		return created;
	}


	public void setCreated(Instant created) {
		this.created = created;
	}


	public boolean isEnabled() {
		return enabled;
	}


	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	

}

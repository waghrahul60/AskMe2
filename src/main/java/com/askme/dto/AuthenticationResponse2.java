package com.askme.dto;

import java.time.Instant;
import java.util.Set;

import com.askme.model.Role;



public class AuthenticationResponse2 {
	 private String authenticationToken;
	 private String refreshToken;
	 private Instant expiresAt;
	 private String username;
	 private Set<Role> roles;
	 private Long id;
	 
	 
	public AuthenticationResponse2() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AuthenticationResponse2(String authenticationToken, String refreshToken, Instant expiresAt, String username,
			Set<Role> roles, Long id) {
		super();
		this.authenticationToken = authenticationToken;
		this.refreshToken = refreshToken;
		this.expiresAt = expiresAt;
		this.username = username;
		this.roles = roles;
		this.id = id;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getAuthenticationToken() {
		return authenticationToken;
	}
	public void setAuthenticationToken(String authenticationToken) {
		this.authenticationToken = authenticationToken;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	public Instant getExpiresAt() {
		return expiresAt;
	}
	public void setExpiresAt(Instant expiresAt) {
		this.expiresAt = expiresAt;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public static Object builder() {
		// TODO Auto-generated method stub
		return null;
	}
}

package com.askme.model;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RefreshToken {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String token;
	    private Instant createdDate;
		public RefreshToken() {
			super();
			// TODO Auto-generated constructor stub
		}
		public RefreshToken(Long id, String token, Instant createdDate) {
			super();
			this.id = id;
			this.token = token;
			this.createdDate = createdDate;
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getToken() {
			return token;
		}
		public void setToken(String token) {
			this.token = token;
		}
		public Instant getCreatedDate() {
			return createdDate;
		}
		public void setCreatedDate(Instant createdDate) {
			this.createdDate = createdDate;
		}
		@Override
		public String toString() {
			return "RefreshToken [id=" + id + ", token=" + token + ", createdDate=" + createdDate + "]";
		}
}

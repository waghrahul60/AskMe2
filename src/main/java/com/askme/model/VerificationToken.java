package com.askme.model;

import java.time.Instant;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "token")
public class VerificationToken {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String token;
	    
	    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	    private User user;
	    private Instant expiryDate;
		public VerificationToken() {
			super();
			// TODO Auto-generated constructor stub
		}
		public VerificationToken(Long id, String token, User user, Instant expiryDate) {
			super();
			this.id = id;
			this.token = token;
			this.user = user;
			this.expiryDate = expiryDate;
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
		public User getUser() {
			return user;
		}
		public void setUser(User user) {
			this.user = user;
		}
		public Instant getExpiryDate() {
			return expiryDate;
		}
		public void setExpiryDate(Instant expiryDate) {
			this.expiryDate = expiryDate;
		}
		@Override
		public String toString() {
			return "VerificationToken [id=" + id + ", token=" + token + ", user=" + user + ", expiryDate=" + expiryDate
					+ "]";
		}	
	    
	    

}

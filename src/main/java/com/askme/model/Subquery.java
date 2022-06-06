package com.askme.model;

import java.time.Instant;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;



@Entity
public class Subquery {
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    @NotBlank(message = "Community name is required")
	    private String name;
	    @NotBlank(message = "Description is required")
	    private String description;
	    
	    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	    private List<Post> posts;
	    
	    private Instant createdDate;
	    
	    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	    private User user;

		public Subquery() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Subquery(Long id, @NotBlank(message = "Community name is required") String name,
				@NotBlank(message = "Description is required") String description, List<Post> posts,
				Instant createdDate, User user) {
			super();
			this.id = id;
			this.name = name;
			this.description = description;
			this.posts = posts;
			this.createdDate = createdDate;
			this.user = user;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public List<Post> getPosts() {
			return posts;
		}

		public void setPosts(List<Post> posts) {
			this.posts = posts;
		}

		public Instant getCreatedDate() {
			return createdDate;
		}

		public void setCreatedDate(Instant createdDate) {
			this.createdDate = createdDate;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		@Override
		public String toString() {
			return "Subquery [id=" + id + ", name=" + name + ", description=" + description + ", posts=" + posts
					+ ", createdDate=" + createdDate + ", user=" + user + "]";
		}

		public static Object builder() {
			// TODO Auto-generated method stub
			return null;
		}
	    
	    
	    
}

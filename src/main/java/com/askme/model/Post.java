package com.askme.model;

import java.time.Instant;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import org.springframework.lang.Nullable;

@Entity
public class Post {
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long postId;
	    @NotBlank(message = "Post Name cannot be empty or Null")
	    private String postName;
	    @Nullable
	    private String url;
	    @Nullable
	    @Lob
	    private String description;
	    private Integer voteCount = 0;
	    
	    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	    @JoinColumn(name = "userId", referencedColumnName = "userId")
	    private User user;
	    
	    
	    private Instant createdDate;
	    
	    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	    @JoinColumn(name = "id", referencedColumnName = "id")
	    private Subquery subquery;

		public Post() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Post(Long postId, @NotBlank(message = "Post Name cannot be empty or Null") String postName, String url,
				String description, Integer voteCount, User user, Instant createdDate, Subquery subquery) {
			super();
			this.postId = postId;
			this.postName = postName;
			this.url = url;
			this.description = description;
			this.voteCount = voteCount;
			this.user = user;
			this.createdDate = createdDate;
			this.subquery = subquery;
		}

		public Long getPostId() {
			return postId;
		}

		public void setPostId(Long postId) {
			this.postId = postId;
		}

		public String getPostName() {
			return postName;
		}

		public void setPostName(String postName) {
			this.postName = postName;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public Integer getVoteCount() {
			return voteCount;
		}

		public void setVoteCount(Integer voteCount) {
			this.voteCount = voteCount;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public Instant getCreatedDate() {
			return createdDate;
		}

		public void setCreatedDate(Instant createdDate) {
			this.createdDate = createdDate;
		}

		public Subquery getSubquery() {
			return subquery;
		}

		public void setSubquery(Subquery subquery) {
			this.subquery = subquery;
		}

		@Override
		public String toString() {
			return "Post [postId=" + postId + ", postName=" + postName + ", url=" + url + ", description=" + description
					+ ", voteCount=" + voteCount + ", user=" + user + ", createdDate=" + createdDate + ", subquery="
					+ subquery + "]";
		}
	    
	    
	    

}

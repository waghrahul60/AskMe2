package com.askme.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.sun.istack.NotNull;

@Entity
public class Vote {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long voteId;
	    private VoteType voteType;
	    
	    @NotNull
	    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	    @JoinColumn(name = "postId", referencedColumnName = "postId")
	    private Post post;
	    
	    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	    @JoinColumn(name = "userId", referencedColumnName = "userId")
	    private User user;

		public Vote() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Vote(Long voteId, VoteType voteType, Post post, User user) {
			super();
			this.voteId = voteId;
			this.voteType = voteType;
			this.post = post;
			this.user = user;
		}

		public Long getVoteId() {
			return voteId;
		}

		public void setVoteId(Long voteId) {
			this.voteId = voteId;
		}

		public VoteType getVoteType() {
			return voteType;
		}

		public void setVoteType(VoteType voteType) {
			this.voteType = voteType;
		}

		public Post getPost() {
			return post;
		}

		public void setPost(Post post) {
			this.post = post;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		@Override
		public String toString() {
			return "Vote [voteId=" + voteId + ", voteType=" + voteType + ", post=" + post + ", user=" + user + "]";
		}
}

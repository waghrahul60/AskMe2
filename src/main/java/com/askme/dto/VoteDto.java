package com.askme.dto;

import com.askme.model.VoteType;

public class VoteDto {
	
	private VoteType voteType;
	private Long postId;
	public VoteDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public VoteDto(VoteType voteType, Long postId) {
		super();
		this.voteType = voteType;
		this.postId = postId;
	}


	public VoteType getVoteType() {
		return voteType;
	}
	public void setVoteType(VoteType voteType) {
		this.voteType = voteType;
	}
	public Long getPostId() {
		return postId;
	}
	public void setPostId(Long postId) {
		this.postId = postId;
	}
	 
	 
	 

}

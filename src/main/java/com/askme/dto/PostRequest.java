package com.askme.dto;

public class PostRequest {
	 private Long postId;
	 private String subqueryName;
	 private String postName;
	 private String url;
	 private String description;
	 
	 
	 
	public PostRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PostRequest(Long postId, String subqueryName, String postName, String url, String description) {
		super();
		this.postId = postId;
		this.subqueryName = subqueryName;
		this.postName = postName;
		this.url = url;
		this.description = description;
	}
	public Long getPostId() {
		return postId;
	}
	public void setPostId(Long postId) {
		this.postId = postId;
	}
	public String getSubqueryName() {
		return subqueryName;
	}
	public void setSubqueryName(String subqueryName) {
		this.subqueryName = subqueryName;
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
	@Override
	public String toString() {
		return "PostRequest [postId=" + postId + ", subqueryName=" + subqueryName + ", postName=" + postName + ", url="
				+ url + ", description=" + description + "]";
	}
}

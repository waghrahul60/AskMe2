package com.askme.dto;

public class SubqueryDto {
	  private Long id;
	  private String name;
	  private String description;
	  private Integer numberOfPosts;
	 
	public SubqueryDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SubqueryDto(Long id, String name, String description, Integer numberOfPosts) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.numberOfPosts = numberOfPosts;
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

	public Integer getNumberOfPosts() {
		return numberOfPosts;
	}

	public void setNumberOfPosts(Integer numberOfPosts) {
		this.numberOfPosts = numberOfPosts;
	}
	
}

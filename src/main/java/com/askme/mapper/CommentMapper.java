package com.askme.mapper;

import com.github.marlonlom.utilities.timeago.TimeAgo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.askme.dto.CommentsDto;
import com.askme.model.Comment;
import com.askme.model.Post;
import com.askme.model.User;

@Mapper(componentModel = "spring")
public abstract class CommentMapper {
	 @Mapping(target = "id", ignore = true)
	 @Mapping(target = "text", source = "commentsDto.text")
	 @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
	 @Mapping(target = "post", source = "post")
	 @Mapping(target = "user", source = "user")
	 public abstract Comment map(CommentsDto commentsDto, Post post, User user);
	 
	 
	 
	 @Mapping(target = "postId", expression = "java(comment.getPost().getPostId())")
	 @Mapping(target = "duration", expression = "java(getDuration(comment))")
	 @Mapping(target = "userName", expression = "java(comment.getUser().getUserName()")
	 public abstract CommentsDto mapToDto(Comment comment);
	 
	 String getDuration(Comment comment) {
	       // return post.getCreatedDate().toString();
	    	return TimeAgo.using(comment.getCreatedDate().toEpochMilli());
	}
	 
}

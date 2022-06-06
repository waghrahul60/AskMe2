package com.askme.mapper;

import com.github.marlonlom.utilities.timeago.TimeAgo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.askme.dto.PostRequest;
import com.askme.dto.PostResponse;
import com.askme.model.Post;
import com.askme.model.Subquery;
import com.askme.model.User;
import com.askme.model.Vote;
import com.askme.model.VoteType;
import com.askme.repository.CommentRepository;
import com.askme.repository.VoteRepository;
import com.askme.service.AuthService;
//import com.github.marlonlom.utilities.timeago.TimeAgo;

import static com.askme.model.VoteType.UPVOTE;

import java.util.Optional;

import static com.askme.model.VoteType.DOWNVOTE;

@Mapper(componentModel = "spring")
public abstract class PostMapper {
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private CommentRepository commentRepository;
	
	
	@Autowired
	private VoteRepository voteRepository;
	
	
	@Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "description", source = "postRequest.description")
    @Mapping(target = "subquery", source = "subquery")
    @Mapping(target = "user", source = "user")
	@Mapping(target = "voteCount", constant = "0")
	public abstract Post map(PostRequest postRequest, Subquery subquery, User user);
	
	
	@Mapping(target = "id", source = "postId")
	@Mapping(target = "subqueryName", source = "subquery.name")
	@Mapping(target = "userName", source = "user.username")
	@Mapping(target = "commentCount", expression = "java(commentCount(post))")
	@Mapping(target = "duration", expression = "java(getDuration(post))")
	@Mapping(target = "upVote", expression = "java(isPostUpVoted(post))")
	@Mapping(target = "downVote", expression = "java(isPostDownVoted(post))")
	public abstract PostResponse mapToDto(Post post);
	
	Integer commentCount(Post post) {
        return commentRepository.findByPost(post).size();
    }

    String getDuration(Post post) {
       // return post.getCreatedDate().toString();
    	return TimeAgo.using(post.getCreatedDate().toEpochMilli());
    }

    boolean isPostUpVoted(Post post) {
        return checkVoteType(post, UPVOTE);
    }

    boolean isPostDownVoted(Post post) {
        return checkVoteType(post, DOWNVOTE);
    }

    private boolean checkVoteType(Post post, VoteType voteType) {
        if (authService.isLoggedIn()) {
            Optional<Vote> voteForPost = voteRepository.findTopByPostAndUserOrderByVoteIdDesc(post, authService.getCurrentUser());
            
            return voteForPost.filter(vote -> vote.getVoteType().equals(voteType)).isPresent();
        }
        return false;
    }
}

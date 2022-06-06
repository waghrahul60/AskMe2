package com.askme.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.askme.dto.VoteDto;
import com.askme.exceptions.PostNotFoundException;
import com.askme.exceptions.askmeException;
import com.askme.model.Post;
import com.askme.model.Vote;
import com.askme.repository.PostRepository;
import com.askme.repository.VoteRepository;


import static com.askme.model.VoteType.UPVOTE;


@Service
public class VoteService {
	
	@Autowired
	private VoteRepository voteRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private AuthService authService;
	
	public void vote(VoteDto voteDto) {
        Post post = postRepository.findById(voteDto.getPostId())
                .orElseThrow(() -> new PostNotFoundException("Post Not Found with post ID =>" + voteDto.getPostId()));
        
        
        Optional<Vote> voteByPostAndUser = voteRepository.findTopByPostAndUserOrderByVoteIdDesc(post, authService.getCurrentUser());
        
        
        if (voteByPostAndUser.isPresent() && voteByPostAndUser.get().getVoteType().equals(voteDto.getVoteType())) {
            throw new askmeException("You have already " + voteDto.getVoteType() + "'voted for this post");
        }
        if (UPVOTE.equals(voteDto.getVoteType())) {
            post.setVoteCount(post.getVoteCount() + 1);
        } else {
            post.setVoteCount(post.getVoteCount() - 1);
        }
        voteRepository.save(mapToVote(voteDto, post));
        postRepository.save(post);
    }
	
	
	private Vote mapToVote(VoteDto voteDto, Post post) {
//        return Vote.builder()
//                .voteType(voteDto.getVoteType())
//                .post(post)
//                .user(authService.getCurrentUser())
//                .build();
		
		return new Vote(post.getPostId(),voteDto.getVoteType(),post,authService.getCurrentUser());
    }
}

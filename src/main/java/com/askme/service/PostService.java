package com.askme.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.askme.dto.PostRequest;
import com.askme.dto.PostResponse;
import com.askme.exceptions.PostNotFoundException;
import com.askme.exceptions.SubqueryNotFoundException;
import com.askme.mapper.PostMapper;
import com.askme.model.Post;
import com.askme.model.Subquery;
import com.askme.model.User;
import com.askme.repository.PostRepository;
import com.askme.repository.SubqueryRepository;
import com.askme.repository.UserRepository;

import static java.util.stream.Collectors.toList;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private  SubqueryRepository subqueryRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private PostMapper postMapper;
	
	public void save(PostRequest postRequest) {
        Subquery subquery = subqueryRepository.findByName(postRequest.getSubqueryName())
                .orElseThrow(() -> new SubqueryNotFoundException(postRequest.getSubqueryName()));
        postRepository.save(postMapper.map(postRequest, subquery, authService.getCurrentUser()));
    }
	
	 @Transactional(readOnly = true)
	 public List<PostResponse> getPostsByUsername(String username) {
	        User user = userRepository.findByUsername(username)
	                .orElseThrow(() -> new UsernameNotFoundException(username));
	        return postRepository.findByUser(user)
	                .stream()
	                .map(postMapper::mapToDto)
	                .collect(toList());
	 }

    @Transactional
    public PostResponse getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id.toString()));
        return postMapper.mapToDto(post);
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsBySubquery(Long subqueryId) {
        Subquery subquery = subqueryRepository.findById(subqueryId)
                .orElseThrow(() -> new SubqueryNotFoundException(subqueryId.toString()));
        List<Post> posts = postRepository.findAllBySubquery(subquery);
        return posts.stream().map(postMapper::mapToDto).collect(toList());
    }
    
    @Transactional(readOnly = true)
    public List<PostResponse> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(postMapper::mapToDto)
                .collect(toList());
    }
    
    public  int deleteById(Long postId,Long userId) {
		return postRepository.deletePostByIdAndUserId(postId, userId);
	}

   
	
}

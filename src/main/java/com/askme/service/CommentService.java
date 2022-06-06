package com.askme.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.askme.dto.CommentsDto;
import com.askme.exceptions.PostNotFoundException;
import com.askme.mapper.CommentMapper;
import com.askme.model.Comment;
import com.askme.model.NotificationEmail;
import com.askme.model.Post;
import com.askme.model.User;
import com.askme.repository.CommentRepository;
import com.askme.repository.PostRepository;
import com.askme.repository.UserRepository;

import static java.util.stream.Collectors.toList;

@Service
public class CommentService {
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private CommentMapper commentMapper;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private MailContentBuilder mailContentBuilder;
	
	@Autowired
	private MailService mailService;
	
	private static String POST_URL = "";
	
	
	public void save(CommentsDto commentsDto) {
		Post post = postRepository.findById(commentsDto.getPostId())
				.orElseThrow(() -> new PostNotFoundException(commentsDto.getPostId().toString()));
		Comment comment = commentMapper.map(commentsDto, post, authService.getCurrentUser());
		commentRepository.save(comment);
		String message = mailContentBuilder.build(authService.getCurrentUser() + "added comment on your post " + POST_URL);
		sendCommentNotification(message, post.getUser());
		
	}
	
	private void sendCommentNotification(String message, User user) {
		mailService.sendMail(new NotificationEmail(user.getUsername() + "commented on your post", user.getEmail(), message));
	}
	
	public List<CommentsDto> getAllCommentsForPost(Long postId){
		Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException(postId.toString()));
		return commentRepository.findByPost(post)
				.stream()
				.map(commentMapper::mapToDto).collect(toList());
	}
	
	public List<CommentsDto> getAllCommentsForUser(String userName){
		User user = userRepository.findByUsername(userName).orElseThrow(() -> new UsernameNotFoundException(userName));
		return commentRepository.findAllByUser(user)
				.stream()
				.map(commentMapper::mapToDto).collect(toList());
	}
	
	
	public  int deleteById(Long id,Long userId) {
		return commentRepository.deleteCommentByIdAndUserId(id, userId);
	}
	
	
	
}

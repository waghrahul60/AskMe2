package com.askme.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.askme.dto.CommentsDto;
import com.askme.service.CommentService;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/comments/")
public class CommentsController {
	
	@Autowired
	private CommentService commentService;
	
	@PostMapping
	public ResponseEntity<Void>createComment(@RequestBody CommentsDto commentsDto){
		commentService.save(commentsDto);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	
	@GetMapping("by-post/{postId}")
	public ResponseEntity<List<CommentsDto>> getAllCommentsForPost(@PathVariable Long postId){
		commentService.getAllCommentsForPost(postId);
		return ResponseEntity.status(HttpStatus.OK).body(commentService.getAllCommentsForPost(postId));
	}
	
	@GetMapping("by-user/{userName}")
	public ResponseEntity<List<CommentsDto>> getAllCommentsForUser(@PathVariable String userName){
		commentService.getAllCommentsForUser(userName);
		return ResponseEntity.status(HttpStatus.OK).body(commentService.getAllCommentsForUser(userName));
	}
	
	@GetMapping("delete/{id}")
	public void deleteCommentById(@PathVariable Long id) {
		//commentService.deleteById(id);
	}
	@GetMapping("delete/{id}/{userId}")
	public ResponseEntity<Integer>  deleteCommentById(@PathVariable Long id,@PathVariable Long userId) {
		return  ResponseEntity.status(HttpStatus.OK).body(commentService.deleteById(id, userId));
	}
}

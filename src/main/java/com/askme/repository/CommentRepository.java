package com.askme.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.askme.model.Comment;
import com.askme.model.Post;
import com.askme.model.User;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{
	
	   List<Comment> findByPost(Post post);
	   
	   List<Comment> findAllByUser(User user);
	   
	   @Modifying
	   @Query("delete from Comment b where b.id=:id")
	   void deleteComment(@Param("id") int id);
	  
		
		@Modifying
		@Transactional
		@Query(value="delete from comment where id=:id and user_id=:userId",nativeQuery = true)
		public int deleteCommentByIdAndUserId(@Param(value="id") long id, @Param(value="userId") long userId);
}

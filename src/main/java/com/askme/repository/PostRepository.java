package com.askme.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.askme.model.Post;
import com.askme.model.Subquery;
import com.askme.model.User;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
	
	List<Post> findByUser(User user);
	
	List<Post> findAllBySubquery(Subquery subquery);
	
	
	@Modifying
	@Transactional
	@Query(value="delete from comment where post_id=postId",nativeQuery = true)
	public int deletePostByIdAndUserId(@Param(value="postId") long postId, @Param(value="userId") long userId);

}

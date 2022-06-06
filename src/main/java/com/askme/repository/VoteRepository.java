package com.askme.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.askme.model.Post;
import com.askme.model.User;
import com.askme.model.Vote;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
	//findTopByPostAndUserOrderByVoteIdDesc
	 Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, User currentUser);
	
}

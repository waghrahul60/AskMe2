package com.askme.repository;


import java.util.Optional;


import javax.transaction.Transactional;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.askme.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User> findByUsername(String username);
	
	
	
	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
	
	Boolean existsByFirstName(String firstName);
	
    Optional<User> findById(Long id);
		
	
//	@Modifying
//	@Transactional
//	@Query(value="update farmer f set f.highest_bid=:hgp where f.pid=:prdctId",nativeQuery = true)
//	public void updateHighestBidPrice(@Param(value = "prdctId") long prdctId,@Param(value = "hgp") double hgp);
	
	@Transactional
	@Query(value="select userId from User where username = :username")
	public long getIdByUsername(@Param(value="username") String username);
	
	@Modifying
	@Transactional
	@Query(value="update user set password=:pass where email = :email",nativeQuery = true)
	public int deleteCommentByIdAndUserId(@Param(value="pass") long id, @Param(value="email") long userId);
	
	@Cascade(CascadeType.ALL)
	@Modifying
	@Transactional
	@Query(value="delete from user where user_id=:id",nativeQuery = true)
	public int deleteUserByUserId(@Param(value="id") long id);
	
	@Modifying
	@Transactional
	@Query(value="update user_roles set  role_id=2 where user_id=:id",nativeQuery = true)
	public int makeAdmin(@Param(value="id") long id);
	
	@Modifying
	@Transactional
	@Query(value="update user_roles set  role_id=1 where user_id=:id",nativeQuery = true)
	public int makeUser(@Param(value="id") long id);
	
}

package com.askme.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import static java.util.stream.Collectors.toList;


import com.askme.dto.UserDto;
import com.askme.exceptions.askmeException;
import com.askme.mapper.UserMapper;
import com.askme.model.User;
import com.askme.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserMapper userMapper;
	
	
	
	 @Transactional
	 public List<UserDto> getAllUser(){
		List<User> user= userRepository.findAll();
		return user.stream().map(userMapper::mapToDto)
				.collect(toList());
	 }
	 
	 @Transactional
	 public UserDto getUser(Long id) {
	        User user = userRepository.findById(id)
	                .orElseThrow(() -> new askmeException("User not found"));
	        return userMapper.mapToDto(user);
	 }
	 
	 @Transactional
	 public void deleteUserById(Long id) {
		 userRepository.deleteById(id);
	 }
	 
	 @Transactional
	 public void makeAdmin(Long id) {
		 userRepository.makeAdmin(id);
	 }
	 
	 @Transactional
	 public void makeUser(Long id) {
		 userRepository.makeUser(id);
	 }
}

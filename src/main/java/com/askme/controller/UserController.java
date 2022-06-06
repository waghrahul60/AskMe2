package com.askme.controller;

import java.util.List;
import java.util.Random;

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

import com.askme.dto.ForgotPasswordRequest;
import com.askme.dto.MessagesResponse;
import com.askme.dto.UserDto;
import com.askme.model.NotificationEmail;
import com.askme.repository.UserRepository;
import com.askme.service.MailService;
import com.askme.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user/")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MailService mailService;
	
	

	@GetMapping("all")
	public ResponseEntity<List<UserDto>> getAllUsers(){
		
		return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUser());
	}
	
	@GetMapping("{id}")
	public ResponseEntity<UserDto> getAllUsers(@PathVariable Long id){
		
		return ResponseEntity.status(HttpStatus.OK).body(userService.getUser(id));
	}
	
	@GetMapping("email/{email}")
	public ResponseEntity<MessagesResponse> emailAlreadyExist(@PathVariable String email) {
		System.out.println(email);
		if (userRepository.existsByEmail(email)) { 	
			
		return ResponseEntity.status(HttpStatus.OK).body(new MessagesResponse("Email Found!!!")); 
	  }else {
		  return ResponseEntity.badRequest().body(new MessagesResponse("Error :Email not Found"));
	  }
	}
	
	@PostMapping("send-otp")
	public ResponseEntity<MessagesResponse> sendOTP(@RequestBody ForgotPasswordRequest forgotPasswordRequest) {
		
		System.out.println(forgotPasswordRequest.getEmail());
		Random random = new Random(1000);
		
		int otp = random.nextInt(9999999);
		mailService.sendMail(new NotificationEmail("Please Check Your Email For OTP",forgotPasswordRequest.getEmail(),
				  "Your OTP is = " +otp));
		
		System.out.println(otp);
		return ResponseEntity.status(HttpStatus.OK).body(new MessagesResponse("Check Your Email!!"));
		
	}
	
	@GetMapping("delete/{id}")
	public void deleteUserById(@PathVariable Long id) {
		System.out.println(id);
		
		userService.deleteUserById(id);
//		if () { 	
//			
//		return ResponseEntity.status(HttpStatus.OK).body(new MessagesResponse("Email Found!!!")); 
//	  }else {
//		  return ResponseEntity.badRequest().body(new MessagesResponse("Error :Email not Found"));
//	  }
	}
	
	@GetMapping("role-admin/{id}")
	public void changeRoleToAdmin(@PathVariable Long id) {
		userService.makeAdmin(id);
	}
	
	@GetMapping("role-user/{id}")
	public void changeUser(@PathVariable Long id) {
		userService.makeUser(id);
	}
	
}

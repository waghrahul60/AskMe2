package com.askme.controller;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.askme.dto.LoginRequest;
import com.askme.dto.MessagesResponse;
import com.askme.dto.RefreshTokenRequest;
import com.askme.dto.SignupRequest;
import com.askme.model.ERole;
import com.askme.model.NotificationEmail;
import com.askme.model.Role;
import com.askme.model.User;
import com.askme.repository.RoleRepository;
import com.askme.repository.UserRepository;
import com.askme.service.AuthService;
import com.askme.service.MailService;
import com.askme.service.UserDetailsImpl;
import com.askme.security.*;
import com.askme.service.RefreshTokenService;
import org.springframework.web.bind.annotation.*;
import com.askme.dto.AuthenticationResponse2;
import com.askme.dto.AuthenticationResponse;
import com.askme.dto.JwtResponse;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
    MailService mailService;
	
	@Autowired
	private RefreshTokenService refreshTokenService;
	
	 
		
	
	@PostMapping("/signup") 
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest) { 
		
	if(userRepository.existsByUsername(signupRequest.getUsername())) {
	return ResponseEntity .badRequest() .body(new MessagesResponse("Error: Username Already Exist")); }
	  
	if (userRepository.existsByEmail(signupRequest.getEmail())) { return
	ResponseEntity .badRequest() .body(new MessagesResponse("Error: Email is already in use!")); }
	
	
		
			  
	  
	  // Create new user's account
	  User user = new User();
	  user.setFirstName(signupRequest.getFirstName());
	  user.setLastName(signupRequest.getLastName());
	  user.setUsername(signupRequest.getUsername());
	  user.setEmail(signupRequest.getEmail());
	  user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
	  user.setCreated(Instant.now()); user.setEnabled(false);
	  user.setWhoAreYou(signupRequest.getWhoAreYou());
	 
	  
	  Set<String> strRoles = signupRequest.getRole(); Set<Role> roles = new
	  HashSet<>();
	  
	  if (strRoles == null) 
	  { 
		  Role userRole = roleRepository.findByName(ERole.ROLE_USER) 
	  .orElseThrow(() -> new RuntimeException("Error: Role is not found.")); 
	  roles.add(userRole); 
	  } else 
	  {
	  strRoles.forEach(role -> { switch (role) { case "admin": Role adminRole =
	  roleRepository.findByName(ERole.ROLE_ADMIN) .orElseThrow(() -> new
	  RuntimeException("Error: Role is not found.")); 
	  roles.add(adminRole);
	  
	  break; default: 
	  Role userRole = roleRepository.findByName(ERole.ROLE_USER)
	  .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	  roles.add(userRole); 
	  } }); }
	  
	  user.setRoles(roles); 
	  userRepository.save(user); 
	  String token = authService.generateVerificationToken(user); 
	  mailService.sendMail(new NotificationEmail("Please Activate Your Account",user.getEmail(),
			  "Thank you for signing up to AskMe.com, " +
	  "please click on the below url to activate your account : " +
	  "http://3.228.58.36:8080/api/auth/accountVerification/" + token));
	  
	  return ResponseEntity.ok(new MessagesResponse("User registered successfully!")); 
	  
	  }
	
	 @GetMapping("accountVerification/{token}") 
	 public ResponseEntity<String> verifyAccount(@PathVariable String token){
		 authService.verifyAccount(token);
	     return new ResponseEntity<>("Account Activated Successfully", HttpStatus.OK);
	 }
	 
	 @PostMapping("/login")
	 public AuthenticationResponse2 login(@RequestBody LoginRequest loginRequest) {
	     return authService.login(loginRequest);
	 }
	 
	 @PostMapping("/refresh/token")
	    public AuthenticationResponse refreshTokens(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
	        return authService.refreshToken(refreshTokenRequest);
	 }
	 
	 @PostMapping("/logout")
	 public ResponseEntity<String> logout(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
	        refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
	        return ResponseEntity.status(HttpStatus.OK).body("Refresh Token Deleted Successfully!!");
	 }
	 
	 
	 

}

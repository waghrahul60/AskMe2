package com.askme.service;

import java.time.Instant;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.askme.dto.AuthenticationResponse;
import com.askme.dto.AuthenticationResponse2;
import com.askme.dto.LoginRequest;
import com.askme.dto.RefreshTokenRequest;
import com.askme.dto.SignupRequest;
import com.askme.exceptions.askmeException;
import com.askme.model.NotificationEmail;
import com.askme.model.Role;
import com.askme.model.User;
import com.askme.model.VerificationToken;
import com.askme.repository.UserRepository;
import com.askme.repository.VerificationTokenRepository;
import com.askme.security.JwtProvider;




@Service
public class AuthService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private VerificationTokenRepository verificationTokenRepository;
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private AuthenticationManager authenticationManager; 
	
	@Autowired
	private JwtProvider jwtProvider;
	
	@Autowired
	private RefreshTokenService refreshTokenService;
	
	public  String s = "";
	
	@Transactional
	public void signup(SignupRequest signupRequest) {
		User user = new User();
		user.setUsername(signupRequest.getUsername());
		user.setEmail(signupRequest.getEmail());
		user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
		user.setCreated(Instant.now());
		user.setEnabled(false);
		
		userRepository.save(user);
		String token = generateVerificationToken(user);
		mailService.sendMail(new NotificationEmail("Please Activate Your Account",user.getEmail(),"Thank you for signing up to Spring Reddit, " +
                "please click on the below url to activate your account : " +
                "http://localhost:8080/api/auth/accountVerification/" + token));
	}
	
	public String generateVerificationToken(User user) {
		String token = UUID.randomUUID().toString();
		VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);

        verificationTokenRepository.save(verificationToken);
        return token;
	}
	
    public void verifyAccount(String token) {
	        Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
	        fetchUserAndEnable(verificationToken.orElseThrow(() -> new askmeException("Invalid Token")));
	}
    
    @Transactional
	private void fetchUserAndEnable(VerificationToken verificationToken) {
		 String username = verificationToken.getUser().getUsername();
	        User user = userRepository.findByUsername(username).orElseThrow(() -> new askmeException("User not found with name - " + username));
	        user.setEnabled(true);
	        userRepository.save(user);
	}
    
    public AuthenticationResponse2 login(LoginRequest loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        s = loginRequest.getUsername();
        String token = jwtProvider.generateToken(authenticate);
//        
//        UserDetailsImpl userDetails = (UserDetailsImpl) authenticate.getPrincipal();	
//        UserDetailsServiceImple user = (UserDetailsServiceImple) authenticate.getPrincipal();	
//		List<String> roles = userDetails.getAuthorities().stream()
//				.map(item -> item.getAuthority())
//				.collect(Collectors.toList());
        
         Optional<User> a =userRepository.findByUsername(loginRequest.getUsername());
         
        
         Set<Role> roles =  a.get().getRoles();
        
		
		String r = refreshTokenService.generateRefreshToken().getToken();
		Instant i =Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis());
		
		Long id = userRepository.getIdByUsername(loginRequest.getUsername());
		
        //return new AuthenticationResponse(token,r,i,loginRequest.getUsername(),roles);
        return new AuthenticationResponse2(token,r,i,loginRequest.getUsername(),roles,id);
//        	return AuthenticationResponse.builder()
//                .authenticationToken(token)
//                .refreshToken(refreshTokenService.generateRefreshToken().getToken())
//                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
//                .username(loginRequest.getUsername())
//                .role(loginRequest.getUsername())
//                .build();
		
    }
    
    public AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.validateRefreshToken(refreshTokenRequest.getRefreshToken());
        String token = jwtProvider.generateTokenWithUserName(refreshTokenRequest.getUsername());
        
        String r = refreshTokenService.generateRefreshToken().getToken();
		Instant i =Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis());
		String a = refreshTokenRequest.getUsername();
        
        return new AuthenticationResponse(token,r,i,a);
//        return AuthenticationResponse.builder()
//                .authenticationToken(token)
//                .refreshToken(refreshTokenRequest.getRefreshToken())
//                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
//                .username(refreshTokenRequest.getUsername())
//                .build();
    }
    
    
    public boolean isLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();
    }
    
    @Transactional
    public User getCurrentUser() {
//    	org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) 
//    			SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	
    	//User a = (User) SecurityContextHolder.getContext().getAuthentication().getDetails();
    	
    	Authentication principal = SecurityContextHolder.getContext().getAuthentication();
    	String username = principal.getPrincipal().toString();
    	
//    	UserDetails userDetails =
//    			 (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	
//    	String s =SecurityContextHolder.getContext().getAuthentication().getName();
    	
    	System.out.println("String is = "+s);
    	
        return userRepository.findByUsername(s)
                .orElseThrow(() -> new UsernameNotFoundException("User name not found - "));// + principal.getUsername()));
    }
    
    
}

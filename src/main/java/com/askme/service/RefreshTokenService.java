package com.askme.service;

import java.time.Instant;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.askme.exceptions.askmeException;
import com.askme.model.RefreshToken;
import com.askme.repository.RefreshTokenRepository;

@Service
@Transactional
public class RefreshTokenService {
	
	@Autowired
    RefreshTokenRepository refreshTokenRepository;
	
	
	 public RefreshToken generateRefreshToken() {
		 
	        RefreshToken refreshToken = new RefreshToken();
	        refreshToken.setToken(UUID.randomUUID().toString());
	        refreshToken.setCreatedDate(Instant.now());

	        return  refreshTokenRepository.save(refreshToken);
	    }
	 void validateRefreshToken(String token) {
	        refreshTokenRepository.findByToken(token)
	                .orElseThrow(() -> new askmeException("Invalid refresh Token"));
	 }

	 public void deleteRefreshToken(String token) {
	        refreshTokenRepository.deleteByToken(token);
	 }

}

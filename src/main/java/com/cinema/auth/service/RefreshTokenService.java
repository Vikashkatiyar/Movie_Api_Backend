/**
 * 
 */
package com.cinema.auth.service;

import java.time.Instant;
import java.util.UUID;

import javax.management.RuntimeErrorException;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cinema.auth.entities.RefreshToken;
import com.cinema.auth.entities.User;
import com.cinema.auth.repositories.RefreshTokenRepository;
import com.cinema.auth.repositories.UserRepository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

/**
 * @author vikash katiyar
 *
 */
@Service
public class RefreshTokenService {

	private final UserRepository userRepository;
	private final RefreshTokenRepository refreshTokenRepository;

	public RefreshTokenService(UserRepository userRepository, RefreshTokenRepository refreshTokenRepository) {
		this.userRepository = userRepository;
		this.refreshTokenRepository = refreshTokenRepository;
	}
	
	public RefreshToken createRefreshToken(String username) {
		User user=userRepository.findByEmail(username)
				.orElseThrow(()-> new UsernameNotFoundException("User not found with email : " + username));
		
		RefreshToken refreshToken=user.getRefreshToken();
		if(refreshToken==null) {
			long refreshTokenValidity=2*60*60*10000; //5*60*60*10000
			refreshToken=RefreshToken.builder()
					.refreshToken(UUID.randomUUID().toString())
					.expirationTime(Instant.now().plusMillis(refreshTokenValidity))
					.user(user)
					.build();
			
			refreshTokenRepository.save(refreshToken);
		}
		
		return refreshToken;
		
	}
	
	public RefreshToken verifyRefreshToken(String refreshToken) {
		RefreshToken refToken= refreshTokenRepository.findByRefreshToken(refreshToken)
		.orElseThrow(()-> new RuntimeException("Refresh token not found!"));
		
		if(refToken.getExpirationTime().compareTo(Instant.now()) < 0) {
			refreshTokenRepository.delete(refToken);
			throw new RuntimeException("Refresh Token Expired");
		}
		
		return refToken;
	}
	
	

}

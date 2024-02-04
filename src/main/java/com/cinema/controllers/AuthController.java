/**
 * 
 */
package com.cinema.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cinema.auth.entities.RefreshToken;
import com.cinema.auth.entities.User;
import com.cinema.auth.service.AuthService;
import com.cinema.auth.service.JwtService;
import com.cinema.auth.service.RefreshTokenService;
import com.cinema.auth.utils.AuthResponse;
import com.cinema.auth.utils.LoginRequest;
import com.cinema.auth.utils.RefreshTokenRequest;
import com.cinema.auth.utils.RegisterRequest;

/**
 * @author vikash katiyar
 *
 */

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {
    
	private final AuthService  authService;
	private final RefreshTokenService refreshTokenService;
    private final JwtService jwtService;
    
	
	
	public AuthController(AuthService authService, RefreshTokenService refreshTokenService, JwtService jwtService) {
		this.authService = authService;
		this.refreshTokenService = refreshTokenService;
		this.jwtService = jwtService;
	}

	@PostMapping("/register")
	public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest registerRequest){
		return ResponseEntity.ok(authService.register(registerRequest));
	}
	
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest){
		return ResponseEntity.ok(authService.login(loginRequest));
	}
	
	@PostMapping("/refresh")
	public ResponseEntity<AuthResponse> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest){
		RefreshToken refreshToken = refreshTokenService.verifyRefreshToken(refreshTokenRequest.getRefreshToken());
		User user=refreshToken.getUser();
		
		String accessToken=jwtService.generateToken(user);
		return ResponseEntity.ok(AuthResponse.builder()
				.accessToken(accessToken)
				.refreshToken(refreshToken.getRefreshToken())
				.build());
	}
	
	
	
	
}

/**
 * 
 */
package com.cinema.auth.entities;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author vikash katiyar
 *
 */
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class RefreshToken {
   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer tokenId;
	
	@Column(nullable = false, length = 500)
	@NotBlank(message = "Please enter refresh token value")
	private String refreshToken;
	
	@Column(nullable = false)
	private Instant expirationTime;
	
	@OneToOne
	private User user;
	
	
}

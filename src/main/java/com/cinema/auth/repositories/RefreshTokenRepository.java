/**
 * 
 */
package com.cinema.auth.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cinema.auth.entities.RefreshToken;

/**
 * @author vikash katiyar
 *
 */
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer> {
  Optional<RefreshToken> findByRefreshToken(String refreshToken);
  
}

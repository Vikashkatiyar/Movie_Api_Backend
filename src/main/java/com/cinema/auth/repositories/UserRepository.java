/**
 * 
 */
package com.cinema.auth.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cinema.auth.entities.User;

/**
 * @author vikash katiyar
 *
 */
public interface UserRepository extends JpaRepository<User, Integer> {
   
	Optional<User> findByEmail(String username);
}

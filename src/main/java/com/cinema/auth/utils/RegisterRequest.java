/**
 * 
 */
package com.cinema.auth.utils;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author vikash katiyar
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {
   
	private String name;
	private String email;
	private String username;
	private String password;
}

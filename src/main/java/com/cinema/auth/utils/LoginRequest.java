/**
 * 
 */
package com.cinema.auth.utils;

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
public class LoginRequest {

    private String email;
    private String password;
}

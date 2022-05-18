
package com.fishcount.api.config.security.provider;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 *
 * @author lucas
 */
public class AppAuthenticationToken extends UsernamePasswordAuthenticationToken {

    public AppAuthenticationToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

}
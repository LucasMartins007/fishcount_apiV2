package com.fishcount.api.config.security.provider;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author lucas
 */
public class AppAuthenticationProvider extends DaoAuthenticationProvider {

    public AppAuthenticationProvider(UserDetailsService detailsService) {
        super.setUserDetailsService(detailsService);
    }

    public AuthenticationProvider passwordEncoder(PasswordEncoder passwordEncoder) {
        super.setPasswordEncoder(passwordEncoder);
        return this;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return AppAuthenticationToken.class.isAssignableFrom(authentication);
    }

}

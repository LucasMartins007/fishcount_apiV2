package com.fishcount.api.config.security;

import com.fishcount.api.config.security.provider.AppAuthenticationProvider;
import com.fishcount.common.utils.ListUtil;
import io.vavr.collection.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

/**
 * @author lucas
 */
@Configuration
public class WebSecurityConfig {

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Value("${api.base-path}/**")
    private String prefixPath;

    private static final List<String> NO_SECURED_URLS = Array.of(
            "/v3/api-docs",
            "/configuration/ui",
            "/swagger-resources/**",
            "/swagger-ui/**",
            "/configuration/security",
            "/webjars/**",
            "/actuator/**",
            "/login",
            "/pessoa"
    ).toJavaList();

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http.authorizeRequests(auth -> auth
                        .antMatchers(ListUtil.toArray(NO_SECURED_URLS))
                        .permitAll()
                        .antMatchers(prefixPath)
                        .authenticated()
                        .anyRequest()
                        .denyAll()
                )
                .requiresChannel()
                .requestMatchers(r -> r.getHeader("X-Forwarded-Proto") != null)
                .requiresSecure()
                .and()
                .formLogin()
                .disable()
                .csrf()
                .disable()
                .httpBasic()
                .disable()
                .build();
    }

    @Autowired
    protected void configure(AuthenticationManagerBuilder auth, UserDetailsService detailsService) {
        auth.authenticationProvider(new AppAuthenticationProvider(detailsService).passwordEncoder(passwordEncoder));
    }

}

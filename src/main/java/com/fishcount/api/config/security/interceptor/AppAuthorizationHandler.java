package com.fishcount.api.config.security.interceptor;

import com.fishcount.api.config.security.JwtTokenUtil;
import com.fishcount.common.exception.FcRuntimeException;
import com.fishcount.common.exception.enums.EnumFcInfraException;
import com.fishcount.common.utils.Utils;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author lucas
 */
public class AppAuthorizationHandler implements HandlerInterceptor {

    @Autowired
    private UserDetailsService jwtUserDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final String requestTokenHeader = request.getHeader("Authorization");

        if (shouldNotFilter(request)) return true;

        validarRequestTokenHeader(requestTokenHeader);

        String jwtToken = requestTokenHeader.replace("Bearer ", "");
        String username = getUsernameFromToken(jwtToken);

        if (Utils.isNotEmpty(username) && Utils.isEmpty(SecurityContextHolder.getContext().getAuthentication())) {
            UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);

            if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
                autenticarUsuario(userDetails, request);
            }
        }

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        SecurityContextHolder.clearContext();
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        SecurityContextHolder.clearContext();
    }

    private boolean shouldNotFilter(HttpServletRequest request) {
        return HttpMethod.OPTIONS.matches(request.getMethod())
                || new AntPathMatcher().match("/api/v1/login", request.getRequestURI())
                || new AntPathMatcher().match("/api/v1/usuario/cadastro", request.getRequestURI());
    }

    private void autenticarUsuario(UserDetails userDetails, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
    }

    private String getUsernameFromToken(final String jwtToken) {
        try {
            return jwtTokenUtil.getUsernameFromToken(jwtToken);
        } catch (Exception e) {
            throw new FcRuntimeException(EnumFcInfraException.TOKEN_INVALIDO);
        }
    }

    private void validarRequestTokenHeader(String requestTokenHeader) {
        if (Utils.isEmpty(requestTokenHeader) || !requestTokenHeader.startsWith("Bearer ")) {
            throw new FcRuntimeException(EnumFcInfraException.TOKEN_NAO_INFORMADO);
        }
    }

}

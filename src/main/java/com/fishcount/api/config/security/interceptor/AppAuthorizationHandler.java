package com.fishcount.api.config.security.interceptor;

import com.fishcount.api.config.security.JwtTokenUtil;
import com.fishcount.api.service.impl.JwtUserDetailsService;
import com.fishcount.common.exception.FcRuntimeException;
import com.fishcount.common.exception.enums.EnumFcInfraException;
import com.fishcount.common.model.pattern.constants.HttpConstants;
import com.fishcount.common.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lucas
 */
public class AppAuthorizationHandler implements HandlerInterceptor {

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final String requestTokenHeader = request.getHeader(HttpConstants.HEADER_AUTHORIZATION);

        validarRequestTokenHeader(requestTokenHeader);

        String jwtToken = requestTokenHeader.replace(HttpConstants.BEARER_AUTH, "");
        String username = getUsernameFromToken(jwtToken);

        if (Utils.isNotEmpty(username) && Utils.isEmpty(SecurityContextHolder.getContext().getAuthentication())) {
            UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);

            if (Boolean.TRUE.equals(JwtTokenUtil.validateToken(jwtToken, userDetails))) {
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

    private void autenticarUsuario(UserDetails userDetails, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
    }

    private String getUsernameFromToken(final String jwtToken) {
        try {
            return JwtTokenUtil.getUsernameFromToken(jwtToken);
        } catch (Exception e) {
            throw new FcRuntimeException(EnumFcInfraException.TOKEN_INVALIDO);
        }
    }

    private void validarRequestTokenHeader(String requestTokenHeader) {
        if (Utils.isEmpty(requestTokenHeader)) {
            throw new FcRuntimeException(EnumFcInfraException.TOKEN_NAO_INFORMADO);
        }
        if (!requestTokenHeader.startsWith(HttpConstants.BEARER_AUTH)) {
            throw new FcRuntimeException(EnumFcInfraException.TOKEN_NAO_INFORMADO);
        }
    }

}

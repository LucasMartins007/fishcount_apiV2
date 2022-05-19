package com.fishcount.api.controller;

import com.fishcount.api.config.security.JwtTokenUtil;
import com.fishcount.api.controller.interfaces.ILoginController;
import com.fishcount.api.service.JwtUserDetailsService;
import com.fishcount.api.service.UsuarioService;
import com.fishcount.common.exception.FcRuntimeException;
import com.fishcount.common.exception.enums.EnumFcDomainException;
import com.fishcount.common.exception.enums.EnumFcInfraException;
import com.fishcount.common.model.classes.UserDTO;
import com.fishcount.common.model.entity.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author lucas
 */
@RestController
@RequiredArgsConstructor
public class LoginController extends AbstractController<UsuarioService> implements ILoginController {

    private final JwtTokenUtil jwtTokenUtil;

    private final JwtUserDetailsService userDetailsService;
    
    private final PasswordEncoder passwordEnconder;

    @Override
    public ResponseEntity<?> authenticate(@RequestBody UserDTO authenticationRequest) throws Exception {
        final Usuario user = getService().findByEmail(authenticationRequest.getUsername());
        
        if (!passwordEnconder.matches(authenticationRequest.getPassword(), user.getSenha())){
            throw new FcRuntimeException(EnumFcDomainException.CREDENCIAIS_INVALIDAS);
        }

        final String token = jwtTokenUtil.generateToken(authenticationRequest.getUsername());
        authenticationRequest.setId(user.getId());
        authenticationRequest.setToken(token);
        authenticationRequest.setPassword(null);

        return ResponseEntity.ok(authenticationRequest);
    }

}

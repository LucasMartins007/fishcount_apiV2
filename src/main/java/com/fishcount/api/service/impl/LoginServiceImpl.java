package com.fishcount.api.service.impl;

import com.fishcount.api.config.security.JwtTokenUtil;
import com.fishcount.api.service.LoginService;
import com.fishcount.api.service.UsuarioService;
import com.fishcount.common.exception.FcRuntimeException;
import com.fishcount.common.exception.enums.EnumFcDomainException;
import com.fishcount.common.model.classes.AutenticacaoDTO;
import com.fishcount.common.model.entity.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl extends AbstractServiceImpl<Usuario, Integer, AutenticacaoDTO> implements LoginService {

    private final JwtTokenUtil jwtTokenUtil;

    private final PasswordEncoder passwordEnconder;

    private final UsuarioService usuarioService;

    @Override
    public AutenticacaoDTO autenticar(AutenticacaoDTO autenticacaoDTO) {
        final Usuario user = usuarioService.findByEmail(autenticacaoDTO.getUsername());

        if (!passwordEnconder.matches(autenticacaoDTO.getPassword(), user.getSenha())){
            throw new FcRuntimeException(EnumFcDomainException.CREDENCIAIS_INVALIDAS);
        }

        final String token = jwtTokenUtil.generateToken(autenticacaoDTO.getUsername());
        autenticacaoDTO.setId(user.getId());
        autenticacaoDTO.setToken(token);
        autenticacaoDTO.setPassword(null);

        return autenticacaoDTO;
    }
}

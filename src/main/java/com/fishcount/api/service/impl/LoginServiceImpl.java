package com.fishcount.api.service.impl;

import com.fishcount.api.config.security.JwtTokenUtil;
import com.fishcount.api.service.LoginService;
import com.fishcount.api.service.PessoaService;
import com.fishcount.api.service.UsuarioService;
import com.fishcount.api.service.pattern.AbstractServiceImpl;
import com.fishcount.common.exception.FcRuntimeException;
import com.fishcount.common.exception.enums.EnumFcDomainException;
import com.fishcount.common.model.dto.AutenticacaoDTO;
import com.fishcount.common.model.entity.Pessoa;
import com.fishcount.common.model.entity.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl extends AbstractServiceImpl<Usuario, Integer, AutenticacaoDTO> implements LoginService {

    private final PasswordEncoder passwordEnconder;

    private final UsuarioService usuarioService;

    @Override
    public AutenticacaoDTO autenticar(AutenticacaoDTO autenticacaoDTO) {
        final Usuario usuario = usuarioService.findByEmail(autenticacaoDTO.getUsername());
        final Pessoa pessoa = getService(PessoaService.class).encontrarPessoaByUsuario(usuario);

        if (!passwordEnconder.matches(autenticacaoDTO.getPassword(), usuario.getSenha())){
            throw new FcRuntimeException(EnumFcDomainException.CREDENCIAIS_INVALIDAS);
        }

        final String token = JwtTokenUtil.generateToken(autenticacaoDTO.getUsername());
        autenticacaoDTO.setId(usuario.getId());
        autenticacaoDTO.setToken(token);
        autenticacaoDTO.setPassword(null);
        autenticacaoDTO.setPessoaId(pessoa.getId());

        return autenticacaoDTO;
    }
}

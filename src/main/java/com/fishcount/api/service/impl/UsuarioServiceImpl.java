package com.fishcount.api.service.impl;

import com.fishcount.api.repository.UsuarioRepository;
import com.fishcount.api.service.EmailService;
import com.fishcount.api.service.UsuarioService;
import com.fishcount.api.service.pattern.AbstractServiceImpl;
import com.fishcount.api.validators.UsuarioValidator;
import com.fishcount.common.exception.FcRuntimeException;
import com.fishcount.common.exception.enums.EnumFcDomainException;
import com.fishcount.common.model.dto.UsuarioDTO;
import com.fishcount.common.model.entity.Email;
import com.fishcount.common.model.entity.Pessoa;
import com.fishcount.common.model.entity.Usuario;
import com.fishcount.common.utils.DateUtil;
import com.fishcount.common.utils.StringUtil;
import com.fishcount.common.utils.optional.OptionalUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author lucas
 */
@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl extends AbstractServiceImpl<Usuario, Integer, UsuarioDTO> implements UsuarioService {

    private final UsuarioValidator usuarioValidator;

    private final UsuarioRepository usuarioRepository;

    private final PasswordEncoder passwordEnconder;

    private final EmailService emailService;

    @Override
    public Usuario incluir(Usuario usuario) {
        usuarioValidator.validateInsert(usuario);

        onPrepareInsert(usuario);

        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario gerarUsuario(Pessoa pessoa) {
        final Usuario usuario = new Usuario();

        final Email email = emailService.encontrarEmailPrincipal(pessoa);
        usuario.setNome(pessoa.getNome());
        usuario.setSenha(pessoa.getSenha());
        usuario.setEmail(email.getDescricao());
        usuario.setAtivo(true);

        return incluir(usuario);
    }

    private void onPrepareInsert(Usuario usuario) {
        usuario.setEmail(StringUtil.toLowerCase(usuario.getEmail()));
        usuario.setSenha(passwordEnconder.encode(usuario.getSenha()));
        usuario.setAtivo(true);
        usuario.setDataInclusao(DateUtil.getDate());
        usuario.setDataAtualizacao(DateUtil.getDate());
    }

    @Override
    public Usuario encontrarPorId(Integer id) {
        return findAndValidate(id);
    }

    @Override
    public Usuario findByEmail(String email) {
        return OptionalUtil.ofFallibleNullable(() -> usuarioRepository.findByEmailPrincipal(email))
                .orElseThrow(() -> new FcRuntimeException(EnumFcDomainException.USUARIO_NAO_ENCONTRADO, email));
    }

}

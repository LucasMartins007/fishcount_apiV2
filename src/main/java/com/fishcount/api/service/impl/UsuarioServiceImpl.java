package com.fishcount.api.service.impl;

import com.fishcount.api.repository.UsuarioRepository;
import com.fishcount.api.service.UsuarioService;
import com.fishcount.api.validators.UsuarioValidator;
import com.fishcount.common.exception.FcRuntimeException;
import com.fishcount.common.exception.enums.EnumFcDomainException;
import com.fishcount.common.model.dto.UsuarioDTO;
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

    private final PasswordEncoder passwordEnconder;

    @Override
    public Usuario incluir(Usuario usuario) {
        usuarioValidator.validateInsert(usuario);

        onPrepareInsert(usuario);

        return getRepository().save(usuario);
    }

    private void onPrepareInsert(Usuario usuario) {
        usuario.setEmail(StringUtil.toLowerCase(usuario.getEmail()));
        usuario.setSenha(passwordEnconder.encode(usuario.getSenha()));
        usuario.setAtivo(true);
        usuario.setDataInclusao(DateUtil.getDate());
        usuario.setDataAlteracao(DateUtil.getDate());
    }

    @Override
    public Usuario encontrarPorId(Integer id) {
        return findAndValidate(id);
    }

    @Override
    public Usuario findByEmail(String email) {
        return OptionalUtil.ofFallible(() -> getRepository(UsuarioRepository.class).findByEmailPrincipal(email))
                .orElseThrow(() -> new FcRuntimeException(EnumFcDomainException.USUARIO_NAO_ENCONTRADO, email));
    }

}

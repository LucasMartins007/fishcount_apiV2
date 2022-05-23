package com.fishcount.api.service.impl;

import com.fishcount.api.repository.UsuarioRepository;
import com.fishcount.api.service.EnderecoService;
import com.fishcount.api.service.LoteService;
import com.fishcount.api.service.TelefoneService;
import com.fishcount.api.service.UsuarioService;
import com.fishcount.api.validators.EmailValidator;
import com.fishcount.api.validators.TelefoneValidator;
import com.fishcount.api.validators.UsuarioValidator;
import com.fishcount.common.exception.FcRuntimeException;
import com.fishcount.common.exception.enums.EnumFcDomainException;
import com.fishcount.common.model.dto.UsuarioDTO;
import com.fishcount.common.model.entity.*;
import com.fishcount.common.utils.DateUtil;
import com.fishcount.common.utils.ListUtil;
import com.fishcount.common.utils.optional.OptionalUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author lucas
 */
@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl extends AbstractServiceImpl<Usuario, Integer, UsuarioDTO> implements UsuarioService {

    private final UsuarioValidator usuarioValidator = new UsuarioValidator();

    private final EmailValidator emailValidator = new EmailValidator();

    private final TelefoneValidator telefoneValidator = new TelefoneValidator();

    private final PasswordEncoder passwordEnconder;

    @Override
    public Usuario incluir(Usuario usuario) {
        usuarioValidator.validateInsert(usuario);

        onPrepareInsert(usuario);

        getRepository().save(usuario);

        return usuario;
    }

    private void onPrepareInsert(Usuario usuario) {
        salvarEmails(usuario);
        salvarTelefones(usuario);
        salvarEnderecos(usuario);
        salvarLotes(usuario);

        usuario.setSenha(passwordEnconder.encode(usuario.getSenha()));
        usuario.setAtivo(true);
        usuario.setDataInclusao(DateUtil.getDate());
        usuario.setDataAlteracao(DateUtil.getDate());
    }

    private void salvarEnderecos(Usuario usuario) {
        if (ListUtil.isNotNullOrNotEmpty(usuario.getEnderecos())) {
            List<Endereco> enderecos = usuario.getEnderecos();
            enderecos.forEach(endereco -> getService(EnderecoService.class).incluir(endereco));
        }
    }

    private void salvarLotes(Usuario usuario) {
        if (ListUtil.isNotNullOrNotEmpty(usuario.getLotes())) {
            List<Lote> lotes = usuario.getLotes();
            lotes.forEach(lote -> getService(LoteService.class).incluir(null, lote));
        }
    }

    private void salvarEmails(Usuario usuario) {
        List<Email> emails = usuario.getEmails();
        emails.forEach(email -> {
            email.setUsuario(usuario);
            email.setAtivo(true);
            emailValidator.validateInsertOrUpdate(email);
        });
    }

    private void salvarTelefones(Usuario usuario) {
        List<Telefone> telefones = usuario.getTelefones();
        telefones.forEach(telefone -> {
            telefone.setUsuario(usuario);
            telefone.setAtivo(true);
            telefoneValidator.validateInsert(telefone);
        });
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

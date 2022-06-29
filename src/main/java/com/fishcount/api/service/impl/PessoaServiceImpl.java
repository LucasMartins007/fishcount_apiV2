package com.fishcount.api.service.impl;

import com.fishcount.api.service.EmailService;
import com.fishcount.api.service.PessoaService;
import com.fishcount.api.service.TelefoneService;
import com.fishcount.api.service.UsuarioService;
import com.fishcount.api.validators.PessoaValidator;
import com.fishcount.common.exception.FcRuntimeException;
import com.fishcount.common.exception.enums.EnumFcDomainException;
import com.fishcount.common.model.dto.PessoaDTO;
import com.fishcount.common.model.entity.Email;
import com.fishcount.common.model.entity.Pessoa;
import com.fishcount.common.model.entity.Usuario;
import com.fishcount.common.model.enums.EnumTipoEmail;
import com.fishcount.common.utils.DateUtil;
import com.fishcount.common.utils.ListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PessoaServiceImpl extends AbstractServiceImpl<Pessoa, Integer, PessoaDTO> implements PessoaService {

    @Autowired
    private PessoaValidator pessoaValidator;

    @Override
    public Pessoa incluir(Pessoa pessoa) {
        pessoaValidator.validateInsert(pessoa);

        onPrepareInsert(pessoa);

        return getRepository().save(pessoa);
    }

    private void onPrepareInsert(Pessoa pessoa) {
        pessoa.setDataInclusao(DateUtil.getDate());
        pessoa.setDaraAlteracao(DateUtil.getDate());

        salvarEmails(pessoa);
        salvarTelefones(pessoa);

        final Usuario usuario = gerarUsuario(pessoa);
        pessoa.setUsuario(usuario);
    }

    private void salvarTelefones(Pessoa pessoa) {
        ListUtil.stream(pessoa.getTelefones())
                .forEach(telefone -> getService(TelefoneService.class).incluir(pessoa.getId(), telefone));
    }

    private void salvarEmails(Pessoa pessoa) {
        ListUtil.stream(pessoa.getEmails())
                .forEach(email -> getService(EmailService.class).incluir(pessoa.getId(), email));
    }

    private Usuario gerarUsuario(Pessoa pessoa) {
        final Usuario usuario = new Usuario();

        final Email email = encontrarEmailPrincipal(pessoa);
        usuario.setNome(pessoa.getNome());
        usuario.setSenha(pessoa.getSenha());
        usuario.setEmail(email.getDescricao());
        usuario.setAtivo(true);

        return getService(UsuarioService.class).incluir(usuario);
    }

    private Email encontrarEmailPrincipal(Pessoa pessoa) {
        return ListUtil.stream(pessoa.getEmails())
                .filter(EnumTipoEmail::isPrincipal)
                .findFirst()
                .orElseThrow(() -> new FcRuntimeException(EnumFcDomainException.EMAIL_PRINCIPAL_NAO_INFORMADO));
    }

}

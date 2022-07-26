package com.fishcount.api.service.impl;

import com.fishcount.api.repository.PessoaRepository;
import com.fishcount.api.service.*;
import com.fishcount.api.service.pattern.AbstractServiceImpl;
import com.fishcount.api.validators.EmailValidator;
import com.fishcount.api.validators.LoteValidator;
import com.fishcount.api.validators.PessoaValidator;
import com.fishcount.api.validators.TelefoneValidator;
import com.fishcount.common.exception.FcRuntimeException;
import com.fishcount.common.exception.enums.EnumFcDomainException;
import com.fishcount.common.model.dto.PessoaDTO;
import com.fishcount.common.model.entity.Email;
import com.fishcount.common.model.entity.Pessoa;
import com.fishcount.common.model.entity.Telefone;
import com.fishcount.common.model.entity.Usuario;
import com.fishcount.common.model.enums.EnumTipoEmail;
import com.fishcount.common.model.enums.EnumTipoEnvioEmail;
import com.fishcount.common.utils.ListUtil;
import com.fishcount.common.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PessoaServiceImpl extends AbstractServiceImpl<Pessoa, Integer, PessoaDTO> implements PessoaService {

    @Autowired
    private PessoaValidator pessoaValidator;

    @Autowired
    private EmailValidator emailValidator;

    @Autowired
    private TelefoneValidator telefoneValidator;

    @Autowired
    private LoteValidator loteValidator;


    @Override
    public Pessoa incluir(Pessoa pessoa) {
        pessoaValidator.validateInsert(pessoa);

        onPrepareInsert(pessoa);

        getRepository().save(pessoa);

        onAfterInsert(pessoa);

        return pessoa;
    }

    @Override
    public Pessoa encontrarPessoa(Integer id) {
        final Pessoa pessoa = findAndValidate(id);

        retirarCamposInativos(pessoa);

        return pessoa;
    }

    @Override
    public void atualizar(Integer id, Pessoa pessoa) {
        final Pessoa managedPessoa = findAndValidate(id);

        onPrepareUpdate(pessoa, managedPessoa);

        getRepository().save(pessoa);
    }

    private void onPrepareUpdate(Pessoa pessoa, Pessoa managedPessoa) {
        pessoa.setId(managedPessoa.getId());
        pessoa.setAtivo(true);
        pessoa.setDataInclusao(managedPessoa.getDataInclusao());
        pessoa.setUsuario(managedPessoa.getUsuario());
        pessoa.setLotes(managedPessoa.getLotes());

        pessoaValidator.validateUpdate(pessoa);
        validarEmails(pessoa);
        validarTelefones(pessoa);
        validarLotes(pessoa);
    }

    private void validarLotes(Pessoa pessoa) {
        ListUtil.stream(pessoa.getLotes())
                .forEach(lote -> {
                    lote.setPessoa(pessoa);
                    loteValidator.validateInsertOrUpdate(lote);

                    if (Utils.isNotEmpty(pessoa.getId())) {
                        getService(LoteService.class).onPrepareInsertOrUpdate(pessoa.getId(), lote);
                    }
                });
    }

    @Override
    public Pessoa encontrarPessoaByUsuario(Usuario usuario) {
        final Pessoa pessoa = getRepository(PessoaRepository.class).findByUsuario(usuario);

        retirarCamposInativos(pessoa);

        return pessoa;
    }

    private void retirarCamposInativos(Pessoa pessoa) {
        final List<Email> emails = ListUtil.stream(pessoa.getEmails())
                .filter(Email::isAtivo)
                .collect(Collectors.toList());

        final List<Telefone> telefones = ListUtil.stream(pessoa.getTelefones())
                .filter(Telefone::isAtivo)
                .collect(Collectors.toList());

        pessoa.setEmails(emails);
        pessoa.setTelefones(telefones);
    }

    private void onPrepareInsert(Pessoa pessoa) {
        validarEmails(pessoa);
        validarTelefones(pessoa);

        final Usuario usuario = gerarUsuario(pessoa);
        pessoa.setUsuario(usuario);
    }

    private void onAfterInsert(Pessoa pessoa) {
        getService(ControleEmailService.class).enviarEmail(pessoa, EnumTipoEnvioEmail.CONFIRMACAO_NOVO_CADASTRO);
    }

    private void validarTelefones(Pessoa pessoa) {
        ListUtil.stream(pessoa.getTelefones())
                .forEach(telefone -> {
                    telefone.setPessoa(pessoa);
                    telefoneValidator.validateInsertOrUpdate(telefone);

                    if (Utils.isNotEmpty(telefone.getId())) {
                        getService(TelefoneService.class).onPrepareUpdate(telefone.getId(), telefone);
                    }
                });
    }

    private void validarEmails(Pessoa pessoa) {
        ListUtil.stream(pessoa.getEmails())
                .forEach(email -> {
                    email.setPessoa(pessoa);
                    emailValidator.validateInsertOrUpdate(email);

                    if (Utils.isNotEmpty(email.getId())) {
                        getService(EmailService.class).onPrepareUpdate(email.getId(), email);
                    }
                });
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

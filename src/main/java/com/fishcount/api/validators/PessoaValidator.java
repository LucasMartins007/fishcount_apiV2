package com.fishcount.api.validators;

import com.fishcount.api.repository.PessoaRepository;
import com.fishcount.api.validators.pattern.AbstractValidatorImpl;
import com.fishcount.api.validators.pattern.ValidateEntity;
import com.fishcount.api.validators.pattern.ValidateMandatoryFields;
import com.fishcount.common.exception.FcRuntimeException;
import com.fishcount.common.exception.enums.EnumFcDomainException;
import com.fishcount.common.model.entity.Pessoa;
import com.fishcount.common.utils.CpfUtil;
import com.fishcount.common.utils.Utils;
import com.fishcount.common.utils.optional.OptionalUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PessoaValidator extends AbstractValidatorImpl<Pessoa> {

    private final PessoaRepository pessoaRepository;

    @Override
    public void validateRequiredFields(Pessoa pessoa) {
        ValidateMandatoryFields validate = new ValidateMandatoryFields();
        validate.add(pessoa.getNome(), "Nome");
        validate.add(pessoa.getEmails(), "Email");
        validate.add(pessoa.getTelefones(), "Telefone");
        validate.add(pessoa.getSenha(), "Senha");

        validate.validate();
    }

    @Override
    public void validateInsert(Pessoa pessoa) {
        validateRequiredFields(pessoa);
        validateSizeFields(pessoa);
    }

    @Override
    public void validateUpdate(Pessoa pessoa) {
        validateSizeFields(pessoa);
        validateCpfDuplicado(pessoa);
        validateCpf(pessoa);
    }

    @Override
    public void validateSizeFields(Pessoa pessoa) {
        ValidateEntity.validateMinMaxCaracterIfFieldNotNull(pessoa.getNome(), 3, 50, "Nome");
    }

    private void validateCpf(Pessoa pessoa) {
        OptionalUtil.ofFallibleNullable(pessoa::getCpf)
                .filter(cpf -> !CpfUtil.isValid(cpf))
                .ifPresentThrow(() -> new FcRuntimeException(EnumFcDomainException.CPF_INVALIDO, pessoa.getCpf()));
    }

    private void validateCpfDuplicado(Pessoa pessoa) {
        OptionalUtil.ofFallibleNullable(pessoa::getCpf)
                .ifPresent(cpf ->
                        OptionalUtil.ofFallibleNullable(() -> pessoaRepository.findByCpf(cpf))
                                .filter(managedPessoa -> !Utils.equals(managedPessoa.getId(), pessoa.getId()))
                                .ifPresentThrow(() -> new FcRuntimeException(EnumFcDomainException.CPF_DUPLICADO, cpf))
                );
    }

}

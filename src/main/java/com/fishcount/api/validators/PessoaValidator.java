package com.fishcount.api.validators;

import com.fishcount.api.validators.pattern.AbstractValidatorImpl;
import com.fishcount.api.validators.pattern.ValidateEntity;
import com.fishcount.api.validators.pattern.ValidateMandatoryFields;
import com.fishcount.common.model.entity.Pessoa;
import org.springframework.stereotype.Component;

@Component
public class PessoaValidator extends AbstractValidatorImpl<Pessoa> {

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
        validateRequiredFields(pessoa);
        validateSizeFields(pessoa);
    }

    @Override
    public void validateSizeFields(Pessoa pessoa) {
        ValidateEntity.validateMinMaxCaracterIfFieldNotNull(pessoa.getNome(), 3, 50, "Nome");
    }
}

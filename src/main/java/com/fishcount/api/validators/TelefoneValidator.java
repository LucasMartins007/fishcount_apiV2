package com.fishcount.api.validators;

import com.fishcount.api.validators.pattern.AbstractValidatorImpl;
import com.fishcount.api.validators.pattern.ValidateEntity;
import com.fishcount.api.validators.pattern.ValidateMandatoryFields;
import com.fishcount.common.model.entity.Telefone;

/**
 *
 * @author lucas
 */
public class TelefoneValidator extends AbstractValidatorImpl<Telefone> {

    @Override
    public void validateRequiredFields(Telefone telefone) {
        ValidateMandatoryFields validate = new ValidateMandatoryFields();

        validate.add(telefone.getDescricao(), "Telefone");
        validate.add(telefone.getTipoTelefone(), "Tipo Telefone");
        validate.add(telefone.getUsuario(), "Usuário");

        validate.validate();
    }

    @Override
    public void validateInsert(Telefone telefone) {
        validateRequiredFields(telefone);
        validateSizeFields(telefone);
        validateFormatoTelefone(telefone);
    }

    @Override
    public void validateSizeFields(Telefone telefone) {
        ValidateEntity.validateMinMaxCaracter(telefone.getDescricao(), 18, 19, "Telefone");
    }

    public void validateFormatoTelefone(Telefone telefone) {
        String descricao = telefone.getDescricao();
        ValidateEntity.validateRegex(descricao, "^\\+[1-9]{2} \\([1-9]{2}\\) (?:[2-8]|9[1-9])[0-9]{3}\\-[0-9]{4}$", Telefone.class.getSimpleName());
    }

}

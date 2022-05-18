package com.fishcount.api.validators;

import com.fishcount.api.service.EmailService;
import com.fishcount.api.validators.pattern.AbstractValidatorImpl;
import com.fishcount.api.validators.pattern.ValidateMandatoryFields;
import com.fishcount.common.exception.FcRuntimeException;
import com.fishcount.common.exception.enums.EnumFcDomainException;
import com.fishcount.common.model.entity.Email;
import com.fishcount.common.utils.StringUtil;
import com.fishcount.common.utils.Utils;

/**
 *
 * @author lucas
 */
public class EmailValidator extends AbstractValidatorImpl<Email> {

    @Override
    public void validateRequiredFields(Email email) {
        ValidateMandatoryFields validate = new ValidateMandatoryFields();

        validate.add(email.getDescricao(), "Email");
        validate.add(email.getTipoEmail(), "Tipo Email");
        validate.add(email.getUsuario(), "Usuário");

        validate.validate();
    }

    @Override
    public void validateInsert(Email email) {
        validateRequiredFields(email);
        validarEmailUsuario(email);
        validateDuplicidadeEmail(email);
    }

    private void validateDuplicidadeEmail(Email email) {
        getService(EmailService.class)
                .findByEmail(email)
                .ifPresent(e -> {
                    throw new FcRuntimeException(EnumFcDomainException.EMAIL_DUPLICADO, e.getDescricao());
                });
    }

    private void validarEmailUsuario(Email email) {
        if (Utils.isNotEmpty(email)) {
            if (!StringUtil.isValidEmail(email.getDescricao())) {
                throw new FcRuntimeException(EnumFcDomainException.EMAIL_INVALIDO, email.getDescricao());
            }
        }
    }

}

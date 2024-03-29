package com.fishcount.api.validators;

import com.fishcount.api.service.EmailService;
import com.fishcount.api.validators.pattern.AbstractValidatorImpl;
import com.fishcount.api.validators.pattern.ValidateMandatoryFields;
import com.fishcount.common.exception.FcRuntimeException;
import com.fishcount.common.exception.enums.EnumFcDomainException;
import com.fishcount.common.model.entity.Email;
import com.fishcount.common.model.entity.Pessoa;
import com.fishcount.common.model.enums.EnumTipoEmail;
import com.fishcount.common.utils.ListUtil;
import com.fishcount.common.utils.StringUtil;
import com.fishcount.common.utils.Utils;
import com.fishcount.common.utils.optional.OptionalUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author lucas
 */
@Component
@RequiredArgsConstructor
public class EmailValidator extends AbstractValidatorImpl<Email> {

    @Override
    public void validateRequiredFields(Email email) {
        ValidateMandatoryFields validate = new ValidateMandatoryFields();

        validate.add(email.getDescricao(), "Email");
        validate.add(email.getTipoEmail(), "Tipo Email");
        validate.add(email.getPessoa(), "Pessoa");

        validate.validate();
    }

    @Override
    public void validateInsertOrUpdate(Email email) {
        validateRequiredFields(email);
        validarEmail(email);
        validateDuplicidadeEmail(email);
        validateEmailPrincipal(email);
    }

    @Override
    public void validateDelete(Email email) {
        validateTipoEmail(email);
    }

    private void validateDuplicidadeEmail(Email email) {
        final Email managedEmail = getService(EmailService.class).findByEmail(email);

        if (Utils.isEmpty(email.getId()) && Utils.isNotEmpty(managedEmail) || (Utils.isNotEmpty(email.getId()) && Utils.isNotEmpty(managedEmail))) {
            throw new FcRuntimeException(EnumFcDomainException.EMAIL_DUPLICADO, email.getDescricao());
        }
    }

    private void validarEmail(Email email) {
        if (Utils.isNotEmpty(email) && !StringUtil.isValidEmail(email.getDescricao())) {
            throw new FcRuntimeException(EnumFcDomainException.EMAIL_INVALIDO, email.getDescricao());
        }
    }

    private void validateTipoEmail(Email email) {
        if (EnumTipoEmail.isPrincipal(email)) {
            throw new FcRuntimeException(EnumFcDomainException.EMAIL_PRINCIPAL_NAO_PODE_SER_INATIVADO, email.getDescricao());
        }
    }

    private void validateEmailPrincipal(Email email) {
        final Pessoa pessoa = email.getPessoa();

        ListUtil.stream(pessoa.getEmails())
                .filter(e -> !e.equals(email) && EnumTipoEmail.isPrincipal(email) && EnumTipoEmail.isPrincipal(e))
                .findAny()
                .ifPresent(e -> {
                    throw new FcRuntimeException(EnumFcDomainException.EMAIL_PRINCIPAL_DUPLICADO);
                });
    }

}

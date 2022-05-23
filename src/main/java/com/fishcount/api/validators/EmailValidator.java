package com.fishcount.api.validators;

import com.fishcount.api.service.EmailService;
import com.fishcount.api.validators.pattern.AbstractValidatorImpl;
import com.fishcount.api.validators.pattern.ValidateMandatoryFields;
import com.fishcount.common.exception.FcRuntimeException;
import com.fishcount.common.exception.enums.EnumFcDomainException;
import com.fishcount.common.model.entity.Email;
import com.fishcount.common.model.entity.Usuario;
import com.fishcount.common.model.enums.EnumTipoEmail;
import com.fishcount.common.utils.ListUtil;
import com.fishcount.common.utils.StringUtil;
import com.fishcount.common.utils.Utils;
import com.fishcount.common.utils.optional.OptionalUtil;

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
    public void validateInsertOrUpdate(Email email) {
        validateRequiredFields(email);
        validarEmailUsuario(email);
        validateDuplicidadeEmail(email);
        validateEmailPrincipal(email);
    }

    @Override
    public void validateDelete(Email email) {
        validateTipoEmail(email);
    }

    private void validateDuplicidadeEmail(Email email) {
        OptionalUtil.ofNullable(getService(EmailService.class).findByEmail(email))
                .filter(e -> Utils.isEmpty(email.getId()) || (Utils.isNotEmpty(email.getId()) && !email.getDescricao().equals(e.getDescricao())))
                .ifPresent(e -> {
                    throw new FcRuntimeException(EnumFcDomainException.EMAIL_DUPLICADO, email.getDescricao());
                });
    }

    private void validarEmailUsuario(Email email) {
        if (Utils.isNotEmpty(email)) {
            if (!StringUtil.isValidEmail(email.getDescricao())) {
                throw new FcRuntimeException(EnumFcDomainException.EMAIL_INVALIDO, email.getDescricao());
            }
        }
    }

    private void validateTipoEmail(Email email) {
        if (EnumTipoEmail.isPrincipal(email)) {
            throw new FcRuntimeException(EnumFcDomainException.EMAIL_PRINCIPAL_NAO_PODE_SER_INATIVADO, email.getDescricao());
        }
    }

    private void validateEmailPrincipal(Email email) {
        final Usuario usuario = email.getUsuario();
        final boolean usuarioHasEmailPrincipal = ListUtil.stream(usuario.getEmails())
                .anyMatch(e -> !e.equals(email) && EnumTipoEmail.isPrincipal(email) && EnumTipoEmail.isPrincipal(e));
                
        if (usuarioHasEmailPrincipal) {
            throw new FcRuntimeException(EnumFcDomainException.EMAIL_PRINCIPAL_DUPLICADO);
        }
    }

}

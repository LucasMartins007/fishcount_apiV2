package com.fishcount.api.validators;

import com.fishcount.api.repository.TelefoneRepository;
import com.fishcount.api.validators.pattern.AbstractValidatorImpl;
import com.fishcount.api.validators.pattern.ValidateEntity;
import com.fishcount.api.validators.pattern.ValidateMandatoryFields;
import com.fishcount.common.exception.FcRuntimeException;
import com.fishcount.common.exception.enums.EnumFcDomainException;
import com.fishcount.common.model.entity.Pessoa;
import com.fishcount.common.model.entity.Telefone;
import com.fishcount.common.model.enums.EnumTipoTelefone;
import com.fishcount.common.utils.Utils;
import com.fishcount.common.utils.optional.OptionalUtil;
import org.springframework.stereotype.Component;

/**
 *
 * @author lucas
 */
@Component
public class TelefoneValidator extends AbstractValidatorImpl<Telefone> {

    @Override
    public void validateRequiredFields(Telefone telefone) {
        ValidateMandatoryFields validate = new ValidateMandatoryFields();

        validate.add(telefone.getDescricao(), "Telefone");
        validate.add(telefone.getTipoTelefone(), "Tipo Telefone");
        validate.add(telefone.getPessoa(), "Pessoa");

        validate.validate();
    }

    @Override
    public void validateInsertOrUpdate(Telefone telefone) {
        validateRequiredFields(telefone);
        validateSizeFields(telefone);
        validateFormatoTelefone(telefone);
        validateDuplicidade(telefone);
        validateTelefonePrincipalDuplicado(telefone);
    }

    @Override
    public void validateDelete(Telefone telefone) {
        validateTipoTelefone(telefone);
    }

    @Override
    public void validateSizeFields(Telefone telefone) {
        ValidateEntity.validateMinMaxCaracter(telefone.getDescricao(), 18, 19, "Telefone");
    }

    public void validateFormatoTelefone(Telefone telefone) {
        final String descricao = telefone.getDescricao();
        ValidateEntity.validateRegex(descricao, "^\\+[1-9]{2} \\([1-9]{2}\\) (?:[2-8]|9[1-9])[0-9]{3}\\-[0-9]{4}$", Telefone.class.getSimpleName());
    }

    private void validateDuplicidade(Telefone telefone) {
        final String descricao = telefone.getDescricao();

        OptionalUtil.ofNullable(getRepository(TelefoneRepository.class).findByDescricao(descricao))
                .filter(tel -> Utils.isEmpty(telefone.getId()) || (Utils.isNotEmpty(telefone.getId()) && !telefone.getDescricao().equals(tel.getDescricao())))
                .ifPresentThrow(() -> new FcRuntimeException(EnumFcDomainException.TELEFONE_DUPLICADO, descricao));
    }

    private void validateTelefonePrincipalDuplicado(Telefone telefone) {
        final Pessoa pessoa = telefone.getPessoa();

        if (Utils.isEmpty(pessoa.getId())) {
            return;
        }

        OptionalUtil.ofNullable(getRepository(TelefoneRepository.class).findByPessoaAndTipo(pessoa, EnumTipoTelefone.PRINCIPAL))
                .ifPresentThrow(() -> new FcRuntimeException(EnumFcDomainException.TELEFONE_PRINCIPAL_DUPLICADO, telefone.getDescricao()));
    }

    private void validateTipoTelefone(Telefone telefone) {
        if (EnumTipoTelefone.isPrincipal(telefone)) {
            throw new FcRuntimeException(EnumFcDomainException.TELEFONE_PRINCIPAL_NAO_PODE_SER_INATIVADO, telefone.getDescricao());
        }
    }

}

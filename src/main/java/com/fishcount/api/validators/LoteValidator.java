package com.fishcount.api.validators;

import com.fishcount.api.repository.LoteRepository;
import com.fishcount.api.validators.pattern.AbstractValidatorImpl;
import com.fishcount.api.validators.pattern.ValidateMandatoryFields;
import com.fishcount.common.exception.FcRuntimeException;
import com.fishcount.common.exception.enums.EnumFcDomainException;
import com.fishcount.common.model.entity.Lote;
import com.fishcount.common.utils.Utils;
import com.fishcount.common.utils.optional.OptionalUtil;

/**
 *
 * @author lucas
 */
public class LoteValidator extends AbstractValidatorImpl<Lote> {

    @Override
    public void validateRequiredFields(Lote lote) {
        ValidateMandatoryFields validate = new ValidateMandatoryFields();

        validate.add(lote.getDescricao(), "Descricao");

        validate.validate();
    }

    @Override
    public void validateInsertOrUpdate(Lote lote) {
        validateRequiredFields(lote);
        validateDuplicidadeLote(lote);
    }

    public void validateDuplicidadeLote(Lote lote) {
        if (Utils.isEmpty(lote.getId())) {
            OptionalUtil.ofNullable(getRepository(LoteRepository.class).findByDescricao(lote.getDescricao()))
                    .ifPresentThrow(() -> new FcRuntimeException(EnumFcDomainException.LOTE_DUPLICADO, lote.getDescricao()));
            return;
        }
        OptionalUtil.ofNullable(getRepository(LoteRepository.class).findByDescricao(lote.getDescricao()))
                .filter(managedLote -> !managedLote.getId().equals(lote.getId()))
                .ifPresentThrow(() -> new FcRuntimeException(EnumFcDomainException.LOTE_DUPLICADO, lote.getDescricao()));
    }

}

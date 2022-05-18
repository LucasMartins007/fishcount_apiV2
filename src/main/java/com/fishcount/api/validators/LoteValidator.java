package com.fishcount.api.validators;

import com.fishcount.api.service.LoteService;
import com.fishcount.api.validators.pattern.AbstractValidatorImpl;
import com.fishcount.api.validators.pattern.ValidateMandatoryFields;
import com.fishcount.common.exception.FcRuntimeException;
import com.fishcount.common.exception.enums.EnumFcDomainException;
import com.fishcount.common.model.entity.Lote;
import com.fishcount.common.utils.Utils;

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
            getService(LoteService.class).findByDescricao(lote)
                    .ifPresent(l -> {
                        throw new FcRuntimeException(EnumFcDomainException.LOTE_DUPLICADO, l.getDescricao());
                    });
        }
        getService(LoteService.class).findByDescricao(lote)
                .filter(managedLote -> !managedLote.getId().equals(lote.getId()))
                .ifPresent(l -> {
                    throw new FcRuntimeException(EnumFcDomainException.LOTE_DUPLICADO, l.getDescricao());
                });
    }

}

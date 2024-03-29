package com.fishcount.api.validators;

import com.fishcount.api.repository.LoteRepository;
import com.fishcount.api.validators.pattern.AbstractValidatorImpl;
import com.fishcount.api.validators.pattern.ValidateMandatoryFields;
import com.fishcount.common.exception.FcRuntimeException;
import com.fishcount.common.exception.enums.EnumFcDomainException;
import com.fishcount.common.model.entity.Lote;
import com.fishcount.common.utils.Utils;
import com.fishcount.common.utils.optional.OptionalUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 *
 * @author lucas
 */
@Component
@RequiredArgsConstructor
public class LoteValidator extends AbstractValidatorImpl<Lote> {

    private final LoteRepository loteRepository;

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
            OptionalUtil.ofNullable(loteRepository.findAtivoByDescricao(lote.getDescricao()))
                    .ifPresentThrow(() -> new FcRuntimeException(EnumFcDomainException.LOTE_DUPLICADO, lote.getDescricao()));
            return;
        }
        OptionalUtil.ofNullable(loteRepository.findAtivoByDescricao(lote.getDescricao()))
                .filter(managedLote -> !managedLote.getId().equals(lote.getId()))
                .ifPresentThrow(() -> new FcRuntimeException(EnumFcDomainException.LOTE_DUPLICADO, lote.getDescricao()));
    }

}

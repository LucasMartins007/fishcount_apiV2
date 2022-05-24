
package com.fishcount.api.validators;

import com.fishcount.api.validators.pattern.AbstractValidatorImpl;
import com.fishcount.api.validators.pattern.ValidateEntity;
import com.fishcount.api.validators.pattern.ValidateMandatoryFields;
import com.fishcount.common.model.entity.TaxaCrescimento;

import java.math.BigDecimal;

/**
 *
 * @author lucas
 */
public class TaxaCrescimentoValidator extends AbstractValidatorImpl<TaxaCrescimento>{

    @Override
    public void validateRequiredFields(TaxaCrescimento taxaCrescimento) {
        ValidateMandatoryFields validate = new ValidateMandatoryFields();
        
        validate.add(taxaCrescimento.getIntervalo(), "Intervalo");
        validate.add(taxaCrescimento.getUnidadeIntervalo(), "Unidade de Intervalo");
        validate.add(taxaCrescimento.getQtdeAumento(), "Quantidade aumento");
        validate.add(taxaCrescimento.getUnidadeAumento(), "Unidade de aumento");
        
        validate.validate();
    }

    @Override
    public void validateInsert(TaxaCrescimento taxaCrescimento) {
        validateRequiredFields(taxaCrescimento);
        validateSizeFields(taxaCrescimento);
    }

    @Override
    public void validateSizeFields(TaxaCrescimento taxaCrescimento) {
        ValidateEntity.validateGreaterThan(taxaCrescimento.getQtdeAumento(), BigDecimal.ONE, "Quantidade Aumento");
        ValidateEntity.validateGreaterThan(BigDecimal.valueOf(taxaCrescimento.getIntervalo()), BigDecimal.ONE, "Quantidade Aumento");
    }

}
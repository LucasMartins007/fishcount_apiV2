
package com.fishcount.api.validators;

import com.fishcount.api.validators.pattern.AbstractValidatorImpl;
import com.fishcount.api.validators.pattern.ValidateEntity;
import com.fishcount.api.validators.pattern.ValidateMandatoryFields;
import com.fishcount.common.model.entity.Especie;

import java.math.BigDecimal;

/**
 *
 * @author lucas
 */
public class EspecieValidator extends AbstractValidatorImpl<Especie>{

    @Override
    public void validateRequiredFields(Especie especie) {
        ValidateMandatoryFields validate = new ValidateMandatoryFields();
        
        validate.add(especie.getDescricao(), "Nome");
        validate.add(especie.getPesoMedio(), "Peso médio");
        validate.add(especie.getQtdeMediaRacao(), "Quantidade média de ração");
        validate.add(especie.getTamanhoMedio(), "Tamanho médio da espécie");
        validate.add(especie.getTaxaCrescimento(), "Taxa crescimento");

        validate.validate();
    }

    @Override
    public void validateInsert(Especie especie) {
        validateRequiredFields(especie);
        validateSizeFields(especie);
    }

    @Override
    public void validateSizeFields(Especie especie) {
        ValidateEntity.validateGreaterThan(especie.getPesoMedio(), BigDecimal.ONE, "Peso Médio");
    }
    
    

}
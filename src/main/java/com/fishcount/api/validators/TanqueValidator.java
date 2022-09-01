
package com.fishcount.api.validators;

import com.fishcount.api.validators.pattern.AbstractValidatorImpl;
import com.fishcount.api.validators.pattern.ValidateMandatoryFields;
import com.fishcount.common.model.entity.Tanque;
import org.springframework.stereotype.Component;

/**
 *
 * @author lucas
 */
@Component
public class TanqueValidator extends AbstractValidatorImpl<Tanque>{

    @Override
    public void validateRequiredFields(Tanque tanque) {
        ValidateMandatoryFields validate = new ValidateMandatoryFields();
        
        validate.add(tanque.getLote(), "Lote");
        validate.add(tanque.getDescricao(), "Descrição");
        validate.add(tanque.getEspecie(), "Espécie");
        validate.add(tanque.getQtdePeixe(), "Quantidade de peixes");
        validate.add(tanque.getPesoInicial(), "Peso inicial");
        validate.add(tanque.isPossuiMedicaoTemperatura(), "Possui medidor temperatura");


        validate.validate();
    }

    @Override
    public void validateInsert(Tanque tanque) {
        validateRequiredFields(tanque);
    }

    @Override
    public void validateInsertOrUpdate(Tanque tanque) {
        validateRequiredFields(tanque);
    }
}
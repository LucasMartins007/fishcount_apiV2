package com.fishcount.api.validators;

import com.fishcount.api.validators.pattern.AbstractValidatorImpl;
import com.fishcount.api.validators.pattern.ValidateEntity;
import com.fishcount.api.validators.pattern.ValidateMandatoryFields;
import com.fishcount.common.model.entity.financeiro.Plano;
import org.springframework.stereotype.Component;

/**
 *
 * @author Lucas Martins
 */
@Component
public class PlanoValidator extends AbstractValidatorImpl<Plano> {

    @Override
    public void validateRequiredFields(Plano plano) {
        ValidateMandatoryFields validate = new ValidateMandatoryFields();

        validate.add(plano.getDescricao(), "Descricao");
        validate.add(plano.getMaxTanque(), "Máximo de tanques");
        validate.add(plano.getMinTanque(), "Mínimo de tanques");
        validate.add(plano.getValorMaximo(), "Valor Máximo");
        validate.add(plano.getValorMinimo(), "Valor Mínimo");
        validate.add(plano.getQtdeParcela(), "Quantidade de Parcelas");

        validate.validate();
    }

    @Override
    public void validateInsert(Plano plano) {
        validateRequiredFields(plano);

    }

    @Override
    public void validateSizeFields(Plano plano) {
        ValidateEntity.validateGreaterThan(plano.getMaxTanque(), plano.getMinTanque(), "Máximo e mínimo de tanques");
        ValidateEntity.validateGreaterThan(plano.getValorMaximo(), plano.getValorMinimo(), "Valor máximo e mínimo do plano");
    }

}

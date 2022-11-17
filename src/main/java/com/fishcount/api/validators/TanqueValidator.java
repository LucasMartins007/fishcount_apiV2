
package com.fishcount.api.validators;

import com.fishcount.api.validators.pattern.AbstractValidatorImpl;
import com.fishcount.api.validators.pattern.ValidateEntity;
import com.fishcount.api.validators.pattern.ValidateMandatoryFields;
import com.fishcount.common.model.entity.Tanque;
import com.fishcount.common.model.enums.EnumUnidadePeso;
import com.fishcount.common.utils.BigDecimalUtil;
import com.fishcount.common.utils.StringUtil;
import com.fishcount.common.utils.Utils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author lucas
 */
@Component
public class TanqueValidator extends AbstractValidatorImpl<Tanque> {

    @Override
    public void validateRequiredFields(Tanque tanque) {
        ValidateMandatoryFields validate = new ValidateMandatoryFields();

        validate.add(tanque.getLote(), "Lote");
        validate.add(tanque.getDescricao(), "Descrição");
        validate.add(tanque.getEspecie(), "Espécie");
        validate.add(tanque.getQtdePeixe(), "Quantidade de peixes");
        validate.add(tanque.getPesoUnitario(), "Peso unitário");
        validate.add(tanque.getUnidadePeso(), "Unidade peso");
        validate.add(tanque.isPossuiMedicaoTemperatura(), "Possui medidor temperatura");


        validate.validate();
    }

    @Override
    public void validateInsert(Tanque tanque) {
        validateRequiredFields(tanque);
        validateSizeFields(tanque);
    }

    @Override
    public void validateInsertOrUpdate(Tanque tanque) {
        validateRequiredFields(tanque);
        validateSizeFields(tanque);
    }

    @Override
    public void validateSizeFields(Tanque tanque) {
        final BigDecimal pesoUnitario = converterKilosEmGramas(tanque);

        ValidateEntity.validateLessThan(pesoUnitario.doubleValue(), 700.0, "Peso unitário", StringUtil.capitalize(tanque.getUnidadePeso().getValue()));
        ValidateEntity.validateGreaterThan(pesoUnitario.doubleValue(), 10.0, "Peso unitário", StringUtil.capitalize(tanque.getUnidadePeso().getValue()));

        ValidateEntity.validateLessThan(tanque.getQtdePeixe(), 10000, "Quantidade de peixes");
    }

    private static BigDecimal converterKilosEmGramas(Tanque tanque) {
        return Utils.equals(tanque.getUnidadePeso(), EnumUnidadePeso.KILO)
                ? BigDecimal.valueOf(1000).multiply(tanque.getPesoUnitario())
                : tanque.getPesoUnitario();
    }
}

package com.fishcount.api.validators;

import com.fishcount.api.validators.pattern.AbstractValidatorImpl;
import com.fishcount.api.validators.pattern.ValidateEntity;
import com.fishcount.api.validators.pattern.ValidateMandatoryFields;
import com.fishcount.common.exception.FcRuntimeException;
import com.fishcount.common.exception.enums.EnumFcDomainException;
import com.fishcount.common.model.entity.Tanque;
import com.fishcount.common.model.enums.EnumUnidadePeso;
import com.fishcount.common.utils.BigDecimalUtil;
import com.fishcount.common.utils.StringUtil;
import com.fishcount.common.utils.Utils;
import org.apache.commons.lang3.StringUtils;
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
        final Integer qtdePeixe = tanque.getQtdePeixe();

        if (pesoUnitario != null && pesoUnitario.doubleValue() >= 700.0) {
            throw new FcRuntimeException(EnumFcDomainException.PESO_UNITARIO_ACIMA_LIMITE, pesoUnitario, StringUtils.capitalize(tanque.getUnidadePeso().getDescricao()));
        }
        if (pesoUnitario != null && pesoUnitario.doubleValue() <= 10.0) {
            throw new FcRuntimeException(EnumFcDomainException.PESO_UNITARIO_ABAIXO_LIMITE, pesoUnitario, StringUtils.capitalize(tanque.getUnidadePeso().getDescricao()));
        }
        if (qtdePeixe != null && qtdePeixe.doubleValue() >= 10000.0) {
            throw new FcRuntimeException(EnumFcDomainException.QTDE_PEIXES_ACIMA_LIMITE, qtdePeixe);
        }
    }

    private static BigDecimal converterKilosEmGramas(Tanque tanque) {
        return Utils.equals(tanque.getUnidadePeso(), EnumUnidadePeso.KILO)
                ? BigDecimal.valueOf(1000).multiply(tanque.getPesoUnitario())
                : tanque.getPesoUnitario();
    }
}
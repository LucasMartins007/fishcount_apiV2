package com.fishcount.api.validators;

import com.fishcount.api.validators.pattern.AbstractValidatorImpl;
import com.fishcount.api.validators.pattern.ValidateMandatoryFields;
import com.fishcount.common.model.entity.PagamentoParcela;

/**
 *
 * @author Lucas Martins
 */
public class PagamentoParcelaValidator extends AbstractValidatorImpl<PagamentoParcela> {

    @Override
    public void validateRequiredFields(PagamentoParcela pagamentoParcela) {
        ValidateMandatoryFields validate = new ValidateMandatoryFields();

        validate.add(pagamentoParcela.getValor(), "Valor");
        validate.add(pagamentoParcela.getSaldo(), "Saldo");
        validate.add(pagamentoParcela.getAcrescimo(), "Acréscimo");
        validate.add(pagamentoParcela.getDesconto(), "Desconto");
        validate.add(pagamentoParcela.getDataVencimento(), "Data de vencimento");
        validate.add(pagamentoParcela.getStatusPagamento(), "Status do pagamento da parcela");
        validate.add(pagamentoParcela.getTipoPagamento(), "Tipo pagamento");

        validate.validate();
    }

    @Override
    public void validateInsert(PagamentoParcela pagamentoParcela) {
        validateRequiredFields(pagamentoParcela);
    }

}

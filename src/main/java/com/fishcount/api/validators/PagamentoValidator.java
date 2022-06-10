package com.fishcount.api.validators;

import com.fishcount.api.validators.pattern.AbstractValidatorImpl;
import com.fishcount.api.validators.pattern.ValidateMandatoryFields;
import com.fishcount.common.model.entity.Pagamento;

/**
 *
 * @author Lucas Martins
 */
public class PagamentoValidator extends AbstractValidatorImpl<Pagamento> {

    @Override
    public void validateRequiredFields(Pagamento pagamento) {
        ValidateMandatoryFields validate = new ValidateMandatoryFields();
        
        validate.add(pagamento.getValor(), "Valor");
        validate.add(pagamento.getDataVencimento(), "Data de vencimento");
        validate.add(pagamento.getSaldo(), "Saldo");
        validate.add(pagamento.getUsuario(), "Usuário");
        validate.add(pagamento.getTipoPagamento(), "Tipo pagamento");
        
        validate.validate();
    }

    @Override
    public void validateInsert(Pagamento pagamento) {
        validateRequiredFields(pagamento);
    }
    
    

}

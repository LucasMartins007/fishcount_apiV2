package com.fishcount.api.validators;

import com.fishcount.api.validators.pattern.AbstractValidatorImpl;
import com.fishcount.common.model.entity.PagamentoParcela;

/**
 *
 * @author Lucas Martins
 */
public class PagamentoParcelaValidator extends AbstractValidatorImpl<PagamentoParcela> {

    @Override
    public void validateRequiredFields(PagamentoParcela entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void validateInsert(PagamentoParcela entity) {
        System.out.println("passou aqui");
        return;
    }
    
    

}

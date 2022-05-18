
package com.fishcount.api.service.impl;

import com.fishcount.api.service.TaxaCrescimentoService;
import com.fishcount.api.validators.TaxaCrescimentoValidator;
import com.fishcount.common.model.dto.TaxaCrescimentoDTO;
import com.fishcount.common.model.entity.TaxaCrescimento;
import com.fishcount.common.utils.DateUtil;
import org.springframework.stereotype.Service;

@Service
public class TaxaCrescimentoServiceImpl extends AbstractServiceImpl<TaxaCrescimento, Integer, TaxaCrescimentoDTO> implements TaxaCrescimentoService {

    private final TaxaCrescimentoValidator validadorTaxaCrescimento = new TaxaCrescimentoValidator();
    
    @Override
    public TaxaCrescimento incluir(TaxaCrescimento taxaCrescimento) {
        validadorTaxaCrescimento.validateInsert(taxaCrescimento);
        
        onPrepareInsert(taxaCrescimento);
        
        return getRepository().save(taxaCrescimento);
    }

    private void onPrepareInsert(TaxaCrescimento taxaCrescimento) {
       taxaCrescimento.setDataAtualizacao(DateUtil.getDate());
       taxaCrescimento.setDataInclusao(DateUtil.getDate());
    }

  
}
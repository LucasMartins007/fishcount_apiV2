
package com.fishcount.api.service.impl;

import com.fishcount.api.service.EspecieService;
import com.fishcount.api.service.TaxaCrescimentoService;
import com.fishcount.api.validators.EspecieValidator;
import com.fishcount.common.model.dto.EspecieDTO;
import com.fishcount.common.model.entity.Especie;
import com.fishcount.common.model.entity.TaxaCrescimento;
import com.fishcount.common.utils.DateUtil;
import org.springframework.stereotype.Service;

@Service
public class EspecieServiceImpl extends AbstractServiceImpl<Especie, Integer, EspecieDTO> implements EspecieService {
    
    private final EspecieValidator especieValidator = new EspecieValidator();
    
    @Override
    public Especie incluir(Especie especie) {
        especieValidator.validateInsert(especie);
        
        onPrepareInsert(especie);
        
        return getRepository().save(especie);
    }

    private void onPrepareInsert(Especie especie) {
        especie.setDataInclusao(DateUtil.getDate());
        especie.setDataAtualizacao(DateUtil.getDate());

        TaxaCrescimento taxaCrescimento = getService(TaxaCrescimentoService.class).incluir(especie.getTaxaCrescimento());
        especie.setTaxaCrescimento(taxaCrescimento);
    }

 
}
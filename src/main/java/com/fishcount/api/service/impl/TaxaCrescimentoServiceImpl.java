
package com.fishcount.api.service.impl;

import com.fishcount.api.repository.TaxaCrescimentoRepository;
import com.fishcount.api.service.EspecieService;
import com.fishcount.api.service.TaxaCrescimentoService;
import com.fishcount.api.validators.TaxaCrescimentoValidator;
import com.fishcount.common.model.dto.TaxaCrescimentoDTO;
import com.fishcount.common.model.entity.Especie;
import com.fishcount.common.model.entity.TaxaCrescimento;
import com.fishcount.common.utils.DateUtil;
import jdk.nashorn.internal.ir.annotations.Reference;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaxaCrescimentoServiceImpl extends AbstractServiceImpl<TaxaCrescimento, Integer, TaxaCrescimentoDTO> implements TaxaCrescimentoService {

    private final TaxaCrescimentoValidator validadorTaxaCrescimento;
    
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

    @Override
    public TaxaCrescimento findByEspecie(String descricaoEspecie) {
       Especie especie = getService(EspecieService.class).findByDescricao(descricaoEspecie);
       
       return getRepository(TaxaCrescimentoRepository.class).findByEspecie(especie);
    }

  
}

package com.fishcount.api.service.impl;

import com.fishcount.api.service.TituloParcelaService;
import com.fishcount.common.model.dto.TituloParcelaDTO;
import com.fishcount.common.model.entity.Titulo;
import com.fishcount.common.model.entity.TituloParcela;
import com.fishcount.common.utils.DateUtil;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Lucas Martins
 */
@Service
public class TituloParcelaServiceImpl extends AbstractServiceImpl<TituloParcela, Integer, TituloParcelaDTO> implements TituloParcelaService {

    @Override
    public TituloParcela incluir(Titulo titulo, TituloParcela parcela) {
        onPrepareInsert(titulo, parcela);
        
        return null;
    }

    private void onPrepareInsert(Titulo titulo, TituloParcela parcela) {
           parcela.setTitulo(titulo);
           parcela.setDataAlteracao(DateUtil.getDate());
           parcela.setAcrescimo(titulo.getAcrescimo());
           parcela.setDataVencimento(titulo.getDataVencimento());
           parcela.setDesconto(titulo.getDesconto());
           parcela.setValor(titulo.getValor());
           parcela.setSaldo(titulo.getSaldo());
    }


}

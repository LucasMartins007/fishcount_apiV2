package com.fishcount.api.service.impl;

import com.fishcount.api.service.CobrancaPixService;
import com.fishcount.api.service.TituloParcelaService;
import com.fishcount.common.model.dto.TituloParcelaDTO;
import com.fishcount.common.model.entity.PagamentoParcela;
import com.fishcount.common.model.entity.Titulo;
import com.fishcount.common.model.entity.TituloParcela;
import com.fishcount.common.model.enums.EnumStatusTitulo;
import com.fishcount.common.model.enums.EnumTipoTitulo;
import com.fishcount.common.utils.DateUtil;
import org.springframework.stereotype.Service;

/**
 *
 * @author Lucas Martins
 */
@Service
public class TituloParcelaServiceImpl
        extends AbstractServiceImpl<TituloParcela, Integer, TituloParcelaDTO>
        implements TituloParcelaService {

    @Override
    public TituloParcela gerarTitulosParcelasByPagamentoParcela(PagamentoParcela parcela) {
        final Titulo titulo = parcela.getPagamento().getTitulo();
        
        final TituloParcela tituloParcela = new TituloParcela();
        tituloParcela.setAcrescimo(parcela.getAcrescimo());
        tituloParcela.setDesconto(parcela.getDesconto());
        tituloParcela.setDataVencimento(parcela.getDataVencimento());
        tituloParcela.setValor(parcela.getValor());
        tituloParcela.setSaldo(parcela.getSaldo());
        tituloParcela.setStatusTitulo(EnumStatusTitulo.ANALISE);
        tituloParcela.setTipoTitulo(EnumTipoTitulo.ENTRADA);
        tituloParcela.setPagamentoParcela(parcela);
        
        onPrepareInsert(titulo, tituloParcela);
        
        return getRepository().save(tituloParcela);
    }

    private void onPrepareInsert(Titulo titulo, TituloParcela tituloParcela) {
        tituloParcela.setDataAlteracao(DateUtil.getDate());
        tituloParcela.setDataInclusao(DateUtil.getDate());
        tituloParcela.setTitulo(titulo);
    }

}
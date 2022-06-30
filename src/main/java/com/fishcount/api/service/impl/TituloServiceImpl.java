package com.fishcount.api.service.impl;

import com.fishcount.api.service.TituloService;
import com.fishcount.api.validators.TituloValidator;
import com.fishcount.common.model.dto.financeiro.TituloDTO;
import com.fishcount.common.model.entity.Pessoa;
import com.fishcount.common.model.entity.financeiro.Plano;
import com.fishcount.common.model.entity.financeiro.Titulo;
import com.fishcount.common.model.enums.EnumStatusTitulo;
import com.fishcount.common.model.enums.EnumTipoTitulo;
import com.fishcount.common.utils.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Calendar;

/**
 *
 * @author Lucas Martins
 */
@Service
@RequiredArgsConstructor
public class TituloServiceImpl extends AbstractServiceImpl<Titulo, Integer, TituloDTO> implements TituloService {

    private final TituloValidator tituloValidator;

    @Override
    public Titulo gerarTituloByPlano(Pessoa pessoa, Plano plano) {
        final Titulo titulo = new Titulo();
        
        titulo.setQtdeParcelas(plano.getQtdeParcela());
        titulo.setStatusTitulo(EnumStatusTitulo.ANALISE);
        titulo.setTipoTitulo(EnumTipoTitulo.ENTRADA);
        titulo.setAcrescimo(BigDecimal.ZERO);
        titulo.setDesconto(BigDecimal.ZERO);
        titulo.setSaldo(BigDecimal.ZERO);
        titulo.setValor(plano.getValorMinimo());
        
        onPrepareInsert(titulo, pessoa);
        
        tituloValidator.validateInsert(titulo);
        
        
        return getRepository().save(titulo);
    }
    
    private void onPrepareInsert(Titulo titulo, Pessoa pessoa){
        titulo.setPessoa(pessoa);
        titulo.setDataAlteracao(DateUtil.getDate());
        titulo.setDataInclusao(DateUtil.getDate());
        titulo.setDataVencimento(DateUtil.add(Calendar.MONTH, 1));
    }

}

package com.fishcount.api.service.impl;

import com.fishcount.api.service.TituloParcelaService;
import com.fishcount.api.service.TituloService;
import com.fishcount.api.validators.TituloValidator;
import com.fishcount.common.model.dto.financeiro.TituloDTO;
import com.fishcount.common.model.entity.financeiro.Plano;
import com.fishcount.common.model.entity.financeiro.Titulo;
import com.fishcount.common.model.entity.Usuario;
import com.fishcount.common.model.enums.EnumStatusTitulo;
import com.fishcount.common.model.enums.EnumTipoTitulo;
import com.fishcount.common.utils.DateUtil;
import java.math.BigDecimal;
import java.util.Calendar;
import org.springframework.stereotype.Service;

/**
 *
 * @author Lucas Martins
 */
@Service
public class TituloServiceImpl extends AbstractServiceImpl<Titulo, Integer, TituloDTO> implements TituloService {

    private final TituloValidator tituloValidator = new TituloValidator();

    @Override
    public Titulo gerarTituloByPlano(Usuario usuario, Plano plano) {
        final Titulo titulo = new Titulo();
        
        titulo.setQtdeParcelas(plano.getQtdeParcela());
        titulo.setStatusTitulo(EnumStatusTitulo.ANALISE);
        titulo.setTipoTitulo(EnumTipoTitulo.ENTRADA);
        titulo.setAcrescimo(BigDecimal.ZERO);
        titulo.setDesconto(BigDecimal.ZERO);
        titulo.setSaldo(BigDecimal.ZERO);
        titulo.setValor(plano.getValorMinimo());
        
        onPrepareInsert(titulo, usuario);
        
        tituloValidator.validateInsert(titulo);
        
        
        return getRepository().save(titulo);
    }
    
    private void onPrepareInsert(Titulo titulo, Usuario usuario){
        titulo.setUsuario(usuario);
        titulo.setDataAlteracao(DateUtil.getDate());
        titulo.setDataInclusao(DateUtil.getDate());
        titulo.setDataVencimento(DateUtil.add(Calendar.MONTH, 1));
    }

}

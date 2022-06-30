package com.fishcount.common.model.dto.financeiro;

import com.fishcount.common.model.dto.pattern.AbstractDTO;
import com.fishcount.common.model.entity.financeiro.Plano;
import com.fishcount.common.model.enums.EnumStatusPagamento;
import com.fishcount.common.model.enums.EnumTipoPagamento;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 *
 * @author Lucas Martins
 */
@Getter
@Setter
public class PagamentoDTO implements AbstractDTO<Integer> {

    private Integer id;

    private BigDecimal valor;

    private BigDecimal saldo;
    
    private BigDecimal acrescimo;
    
    private BigDecimal desconto;
    
    private Plano plano;
    
    private Integer qtdeParcelas;

    private EnumTipoPagamento tipoPagamento;

    private EnumStatusPagamento statusPagamento;

}

package com.fishcount.common.model.dto;

import com.fishcount.common.model.dto.pattern.AbstractDTO;
import com.fishcount.common.model.entity.Plano;
import com.fishcount.common.model.enums.EnumStatusPagamento;
import com.fishcount.common.model.enums.EnumTipoPagamento;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Lucas Martins
 */
@Getter
@Setter
public class PagamentoDTO extends AbstractDTO<Integer> {

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

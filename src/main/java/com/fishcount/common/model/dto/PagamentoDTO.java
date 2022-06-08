package com.fishcount.common.model.dto;

import com.fishcount.common.model.dto.pattern.AbstractDTO;
import com.fishcount.common.model.enums.EnumStatusPagamento;
import com.fishcount.common.model.enums.EnumTipoPagamento;
import java.math.BigDecimal;
import lombok.Getter;

/**
 *
 * @author Lucas Martins
 */
@Getter
public class PagamentoDTO extends AbstractDTO<Integer> {

    private Integer id;

    private BigDecimal valor;

    private BigDecimal saldo;

    private EnumTipoPagamento tipoPagamento;

    private EnumStatusPagamento statusPagamento;

}

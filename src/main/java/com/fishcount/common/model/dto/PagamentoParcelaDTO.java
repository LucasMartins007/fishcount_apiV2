package com.fishcount.common.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fishcount.common.model.dto.pattern.AbstractDTO;
import com.fishcount.common.model.entity.Pagamento;
import com.fishcount.common.model.enums.EnumStatusPagamento;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Lucas Martins
 */
@Getter
@Setter
public class PagamentoParcelaDTO extends AbstractDTO<Integer> {

    private Integer id;

    private BigDecimal valor;

    private BigDecimal saldo;

    private EnumStatusPagamento statusPagamento;
    
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
    private Date dataInclusao;
    
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
    private Date dataVencimento;

}

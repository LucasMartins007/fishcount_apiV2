
package com.fishcount.common.model.dto.financeiro;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fishcount.common.model.dto.pattern.AbstractDTO;
import com.fishcount.common.model.enums.EnumStatusTitulo;
import com.fishcount.common.model.enums.EnumTipoTitulo;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;


/**
 *
 * @author lucas
 */
@Getter
@Setter
public class TituloDTO implements AbstractDTO<Integer> {

    private Integer id;
    
    private BigDecimal valor;
    
    private BigDecimal saldo;

    private BigDecimal desconto;

    private Integer qtdeParcelas;    
   
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
    private Date dataVencimento;
    
    private EnumStatusTitulo statusTitulo;
    
    private EnumTipoTitulo tipoTitulo;
   
}
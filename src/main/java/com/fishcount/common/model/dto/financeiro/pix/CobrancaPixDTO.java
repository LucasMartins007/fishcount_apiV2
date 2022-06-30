
package com.fishcount.common.model.dto.financeiro.pix;

import com.fishcount.common.model.dto.pattern.AbstractDTO;
import com.fishcount.common.model.enums.EnumStatusCobranca;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Lucas Martins
 */
@Getter
@Setter
public class CobrancaPixDTO implements AbstractDTO<Integer>{

    private Integer id;
    
    private String txId;
    
    private BigDecimal valor;
    
    private BigDecimal saldo;
    
    private String chaveDevedor;
    
    private EnumStatusCobranca statusCobranca;
    
    private String observacaoPagador;
    
    private Date dataExpiracao;
    
}

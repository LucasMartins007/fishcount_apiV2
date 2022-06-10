
package com.fishcount.common.model.dto;

import com.fishcount.common.model.dto.pattern.AbstractDTO;
import com.fishcount.common.model.enums.EnumStatusCobranca;
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
public class CobrancaPixDTO extends AbstractDTO<Integer>{

    private Integer id;
    
    private String txId;
    
    private BigDecimal valor;
    
    private BigDecimal saldo;
    
    private String chaveDevedor;
    
    private EnumStatusCobranca statusCobranca;
    
    private String observacaoPagador;
    
    private Date dataExpiracao;
    
}

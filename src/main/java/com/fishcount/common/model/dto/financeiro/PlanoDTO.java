
package com.fishcount.common.model.dto.financeiro;

import com.fishcount.common.model.dto.pattern.AbstractDTO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 *
 * @author lucas
 */
@Getter
@Setter
public class PlanoDTO implements AbstractDTO<Integer> {

    private Integer id;
    
    private String descricao;
    
    private BigDecimal valorMinimo;
    
    private BigDecimal valorMaximo;
    
    private Integer minTanque;
    
    private Integer maxTanque;
    
    private Integer qtdeParcela;
}
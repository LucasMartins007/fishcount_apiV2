
package com.fishcount.common.model.dto.financeiro;

import com.fishcount.common.model.dto.pattern.AbstractDTO;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author lucas
 */
@Getter
@Setter
public class PlanoDTO extends AbstractDTO<Integer> {

    private Integer id;
    
    private String descricao;
    
    private Double valorMinimo;
    
    private Double valorMaximo;
    
    private Integer minTanque;
    
    private Integer maxTanque;
}
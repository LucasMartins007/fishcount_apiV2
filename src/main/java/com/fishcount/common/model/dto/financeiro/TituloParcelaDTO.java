
package com.fishcount.common.model.dto.financeiro;

import com.fishcount.common.model.dto.pattern.AbstractDTO;
import com.fishcount.common.model.enums.EnumStatusTitulo;
import com.fishcount.common.model.enums.EnumTipoTitulo;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 *
 * @author lucas
 */
@Getter
@Setter
public class TituloParcelaDTO extends AbstractDTO<Integer> {

    private Integer id;
    
    private Double valor;
    
    private Double saldo;
    
    private Double acrescimo;
    
    private Double desconto;
    
    private Date dataVencimento;
    
    private EnumStatusTitulo statusTitulo;
    
    private EnumTipoTitulo tipoTitulo;
    
}
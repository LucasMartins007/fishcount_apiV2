
package com.fishcount.common.model.dto;

import com.fishcount.common.model.dto.pattern.AbstractDTO;
import com.fishcount.common.model.enums.EnumUnidadeTamanho;
import com.fishcount.common.model.enums.EnumUnidadeTempo;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


/**
 * @author lucas
 */
@Getter
@Setter
public class TaxaCrescimentoDTO implements AbstractDTO<Integer> {

    private Integer id;

    private BigDecimal qtdeAumento;

    private EnumUnidadeTamanho unidadeAumento;

    private Integer intervalo;

    private EnumUnidadeTempo unidadeIntervalo;
}
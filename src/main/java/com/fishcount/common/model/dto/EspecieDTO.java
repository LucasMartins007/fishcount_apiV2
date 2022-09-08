
package com.fishcount.common.model.dto;

import com.fishcount.common.model.dto.pattern.AbstractDTO;
import com.fishcount.common.model.entity.TaxaCrescimento;
import com.fishcount.common.model.enums.EnumUnidadePeso;
import com.fishcount.common.model.enums.EnumUnidadeTamanho;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 *
 * @author lucas
 */
@Getter
@Setter
public class EspecieDTO implements AbstractDTO<Integer> {

    private Integer id;

    private String descricao;

    private BigDecimal pesoMedio;

    private EnumUnidadePeso unidadePesoMedio;

    private BigDecimal tamanhoMedio;

    private EnumUnidadeTamanho unidadeTamanho;

    private TaxaCrescimentoDTO taxaCrescimento;

    private Integer qtdeMediaRacao;

    private EnumUnidadePeso unidadePesoRacao;

}
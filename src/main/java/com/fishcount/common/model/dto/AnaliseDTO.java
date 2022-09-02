package com.fishcount.common.model.dto;

import com.fishcount.common.model.dto.pattern.AbstractDTO;
import com.fishcount.common.model.enums.EnumStatusAnalise;
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
public class AnaliseDTO implements AbstractDTO<Integer> {

    private Integer id;

    private TanqueDTO tanque;

    private BigDecimal pesoMedioTanque;

    private BigDecimal qtdeMediaRacaoDiaria;

    private BigDecimal qtdeRacaoDiaria;

    private BigDecimal qtdeMediaRacaoRefeicao;

    private BigDecimal qtdeRacaoRefeicao;

    private Integer frequenciaAlimentacaoDiaria;

    private EnumStatusAnalise statusAnalise;

    private Date dateAnalise;


}

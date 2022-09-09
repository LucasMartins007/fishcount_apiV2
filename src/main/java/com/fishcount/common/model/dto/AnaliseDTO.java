package com.fishcount.common.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fishcount.common.model.dto.pattern.AbstractDTO;
import com.fishcount.common.model.entity.Tanque;
import com.fishcount.common.model.enums.EnumStatusAnalise;
import com.fishcount.common.model.enums.EnumUnidadePeso;
import com.fishcount.common.model.pattern.annotations.OnlyField;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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

    private BigDecimal pesoMedioTanque;

    private String tipoRacao;

    private Integer temperaturaAgua;

    private BigDecimal qtdeRacaoDiaria;

    private EnumUnidadePeso unidadePesoRacaoDiaria;

    private BigDecimal qtdeRacaoRefeicao;

    private EnumUnidadePeso unidadePesoRacaoRefeicao;

    private Integer frequenciaAlimentacaoDiaria;

    private EnumStatusAnalise statusAnalise;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dataAnalise;

}

package com.fishcount.common.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fishcount.common.model.dto.pattern.AbstractDTO;
import com.fishcount.common.model.enums.EnumStatusAnalise;
import com.fishcount.common.model.enums.EnumUnidadePeso;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author lucas
 */
@Getter
@Setter
public class TanqueDTO implements AbstractDTO<Integer> {

    private Integer id;
    
    private String descricao;

    private Integer qtdePeixe;

    private BigDecimal pesoUnitario;

    private EnumUnidadePeso unidadePeso;

    private EnumStatusAnalise statusAnalise;

    private boolean possuiMedicaoTemperatura;

    private EspecieDTO especie;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dataInclusao;

    private Integer qtdUltimaAnalise;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dataProximaAnalise;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dataUltimaAnalise;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dataUltimoTratamento;



}

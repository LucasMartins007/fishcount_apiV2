package com.fishcount.common.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fishcount.common.model.dto.pattern.AbstractDTO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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

    private BigDecimal pesoInicial;

    private boolean possuiMedicaoTemperatura;

    private EspecieDTO especie;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dataInclusao;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date ultimaAnalise;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date proximaAnalise;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dataUltimaAnalise;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dataUltimoTratamento;

    private List<AnaliseDTO> analises;

}

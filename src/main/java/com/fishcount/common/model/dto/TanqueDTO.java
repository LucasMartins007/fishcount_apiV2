package com.fishcount.common.model.dto;

import com.fishcount.common.model.dto.pattern.AbstractDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 *
 * @author lucas
 */
@Getter
@Setter
public class TanqueDTO extends AbstractDTO<Integer> {

    private Integer id;
    
    private String descricao;

    private EspecieDTO especie;

    private Date ultimaAnalise;

    private Date proximaAnalise;

    private Date dataUltimaAnalise;

    private Date dataUltimoTratamento;

    private List<AnaliseDTO> analises;

}

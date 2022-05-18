package com.fishcount.common.model.dto;

import com.fishcount.common.model.dto.pattern.AbstractDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 *
 * @author lucas
 */
@Getter
@Setter
public class AnaliseDTO extends AbstractDTO<Integer> {

    private Integer id;

    private Double qtdeRacao;

    private Double pesoMedio;

    private Double qtdeMediaRacao;

    private Date dataAnalise;

}

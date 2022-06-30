package com.fishcount.common.model.dto;

import com.fishcount.common.model.dto.pattern.AbstractDTO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PixHistoricoDTO implements AbstractDTO<Integer> {

    private Integer id;

    private Integer statusCode;

    private String mensagem;

    private BigDecimal valor;

}

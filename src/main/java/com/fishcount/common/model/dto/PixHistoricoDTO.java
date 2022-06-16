package com.fishcount.common.model.dto;

import com.fishcount.common.model.dto.pattern.AbstractDTO;
import com.fishcount.common.model.entity.Usuario;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
public class PixHistoricoDTO extends AbstractDTO<Integer> {

    private Integer id;

    private Integer statusCode;

    private String mensagem;

    private BigDecimal valor;

}

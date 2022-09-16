package com.fishcount.common.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class LoteDTO implements AbstractDTO<Integer> {

    private Integer id;
    
    private String descricao;
    
    private List<TanqueDTO> tanques;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dataInclusao;
}

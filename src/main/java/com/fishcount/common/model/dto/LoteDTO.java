package com.fishcount.common.model.dto;

import com.fishcount.common.model.dto.pattern.AbstractDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 *
 * @author lucas
 */
@Getter
@Setter
public class LoteDTO extends AbstractDTO<Integer> {

    private Integer id;
    
    private String descricao;
    
    private List<TanqueDTO> tanques;

}

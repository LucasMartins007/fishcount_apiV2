
package com.fishcount.common.model.dto.financeiro.pix;

import com.fishcount.common.model.dto.pattern.AbstractDTO;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Lucas Martins
 */
@Getter
@Setter
public class LocationPixDTO extends AbstractDTO<Integer> {

    private Integer id;
    
    private Integer idLocation;
    
    private String location;
    
    private String tipoCob;
    
    private Date dataCriacao;
}

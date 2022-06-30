
package com.fishcount.common.model.dto.financeiro.pix;

import com.fishcount.common.model.dto.pattern.AbstractDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 *
 * @author Lucas Martins
 */
@Getter
@Setter
public class LocationPixDTO implements AbstractDTO<Integer> {

    private Integer id;
    
    private Integer idLocation;
    
    private String location;
    
    private String tipoCob;
    
    private Date dataCriacao;
}

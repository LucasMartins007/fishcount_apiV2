
package com.fishcount.common.model.classes.gerencianet.response;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Lucas Martins
 */
@Getter
@Setter
public class PayloadLocationResponse {

    private Integer id;
    
    private String location;
    
    private String tipoCob;
    
    private Date criacao;
}

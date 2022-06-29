
package com.fishcount.common.model.classes.gerencianet.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

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

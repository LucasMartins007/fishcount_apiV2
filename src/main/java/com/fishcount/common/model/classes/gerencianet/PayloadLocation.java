
package com.fishcount.common.model.classes.gerencianet;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Lucas Martins
 */
@Getter
@Setter
public class PayloadLocation {

    private Integer id;
    
    private String location;
    
    private String tipoCob;
    
    private Date criacao;
}
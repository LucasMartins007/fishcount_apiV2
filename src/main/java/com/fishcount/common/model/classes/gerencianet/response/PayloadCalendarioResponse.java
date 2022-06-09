
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
public class PayloadCalendarioResponse {

    private Date criacao;
    
    private Integer expiracao;
}

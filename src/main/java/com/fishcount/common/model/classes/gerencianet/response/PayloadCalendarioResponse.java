
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
public class PayloadCalendarioResponse {

    private Date criacao;
    
    private Integer expiracao;
}

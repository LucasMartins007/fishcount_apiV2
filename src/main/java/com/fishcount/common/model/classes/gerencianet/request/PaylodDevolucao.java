
package com.fishcount.common.model.classes.gerencianet.request;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Lucas Martins
 */
@Setter 
@Getter
public class PaylodDevolucao {
    
    private String id;
    
    private String rtrId;
    
    private String valor;
    
    private String status;
    
    private PayloadHorario horario;

}

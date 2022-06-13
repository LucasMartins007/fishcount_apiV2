
package com.fishcount.common.model.classes.gerencianet.request;

import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Lucas Martins
 */
@Getter
@Setter
public class PaylodPix {

    private String endToEndId;
    
    private String txId;
    
    private String valor;
    
    private Date horario;
    
    private String infoPagador;
    
    private List<PaylodDevolucao> devolucoes;
}


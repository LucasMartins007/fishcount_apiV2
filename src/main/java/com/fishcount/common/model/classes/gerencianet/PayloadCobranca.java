
package com.fishcount.common.model.classes.gerencianet;

import java.util.List;

import com.fishcount.common.model.enums.EnumStatusCobranca;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Lucas Martins
 * @docRoot https://dev.gerencianet.com.br/docs/api-pix-endpoints
 */
@Getter
@Setter
public class PayloadCobranca {

    private String txId;
    
    private Integer revisao;
        
    private EnumStatusCobranca status;
    
    private String chave;
    
    private String solicitacaoPagador;
    
    private PayloadDevedor devedor;
    
    private PayloadLocation location;
    
    private PayloadValor valor;
    
    private PayloadCalendario calendario;
    
    private List<PaylodPix> pix;
    
}

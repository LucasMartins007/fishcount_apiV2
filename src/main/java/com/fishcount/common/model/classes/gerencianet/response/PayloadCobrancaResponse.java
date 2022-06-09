package com.fishcount.common.model.classes.gerencianet.response;

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
public class PayloadCobrancaResponse {

    private String txId;

    private Integer revisao;

    private String location;

    private EnumStatusCobranca status;

    private String chave;

    private String solicitacaoPagador;
    
    private PayloadCalendarioResponse calendario;
    
    private PayloadLocationResponse loc;
    
    private PayloadDevedorResponse devedor;
    
    private PayloadValorReponse valor;

}

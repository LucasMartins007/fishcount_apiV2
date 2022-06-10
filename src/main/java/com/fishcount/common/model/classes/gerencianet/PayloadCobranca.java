package com.fishcount.common.model.classes.gerencianet;

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

    private String chave;

    private String solicitacaoPagador;

    private PayloadDevedor devedor;

    private PayloadValor valor;

    private PayloadCalendario calendario;

}

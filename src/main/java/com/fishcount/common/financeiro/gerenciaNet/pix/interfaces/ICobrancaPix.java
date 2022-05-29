package com.fishcount.common.financeiro.gerenciaNet.pix.interfaces;

import com.fishcount.common.model.classes.gerencianet.PayloadCobranca;

import java.util.Date;
import java.util.List;

/**
 * Interface de consulta de dados das cobranças PIX da API da GerenciaNET
 *
 * @link https://dev.gerencianet.com.br/docs/api-pix-endpoints
 */
public interface ICobrancaPix {

    PayloadCobranca criarCobranca(String txId, PayloadCobranca payloadCobranca);

    PayloadCobranca revisarCobranca(PayloadCobranca payloadCobranca);

    PayloadCobranca consultarCobranca(String txId);

    PayloadCobranca criarCobrancaImediata(PayloadCobranca payloadCobranca);

    List<PayloadCobranca> listarCobrancas(String cpf, String cnpj, Date dataInicio, Date dataFinal);

}

package com.fishcount.api.service.gerencianet.pix.cobranca;

import com.fishcount.common.model.classes.gerencianet.PayloadCobranca;
import com.fishcount.common.model.classes.gerencianet.response.PayloadCobrancaResponse;

import java.util.Date;
import java.util.List;

public interface ClientCobrancaPix {

    PayloadCobrancaResponse criarCobranca(String txId, PayloadCobranca payloadCobranca);

    PayloadCobranca revisarCobranca(PayloadCobranca payloadCobranca, String txId);

    PayloadCobranca consultarCobranca(String txId);

    PayloadCobrancaResponse criarCobrancaImediata(PayloadCobranca payloadCobranca);

    List<PayloadCobranca> listarCobrancas(String cpf, String cnpj, Date dataInicio, Date dataFinal, String status);
}

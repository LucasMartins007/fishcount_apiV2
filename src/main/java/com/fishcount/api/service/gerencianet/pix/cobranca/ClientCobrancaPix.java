package com.fishcount.api.service.gerencianet.pix.cobranca;

import com.fishcount.common.model.classes.gerencianet.request.PayloadCobranca;
import com.fishcount.common.model.classes.gerencianet.response.PayloadCobrancaResponse;

import java.util.Date;
import java.util.List;

public interface ClientCobrancaPix {

    PayloadCobrancaResponse criarCobranca(String txId, PayloadCobranca payloadCobranca);

    PayloadCobrancaResponse revisarCobranca(PayloadCobranca payloadCobranca, String txId);

    PayloadCobrancaResponse consultarCobranca(String txId);

    PayloadCobrancaResponse criarCobrancaImediata(PayloadCobranca payloadCobranca);

    List<PayloadCobrancaResponse> listarCobrancas(String cpf, String cnpj, Date dataInicio, Date dataFinal, String status);
}

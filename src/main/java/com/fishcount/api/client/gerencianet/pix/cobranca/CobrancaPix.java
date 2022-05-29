package com.fishcount.api.client.gerencianet.pix.cobranca;

import com.fishcount.common.model.classes.gerencianet.PayloadCobranca;

import java.util.Date;
import java.util.List;

public interface CobrancaPix {

    PayloadCobranca criarCobranca(String txId, PayloadCobranca payloadCobranca);

    PayloadCobranca revisarCobranca(PayloadCobranca payloadCobranca);

    PayloadCobranca consultarCobranca(String txId);

    PayloadCobranca criarCobrancaImediata(PayloadCobranca payloadCobranca);

    List<PayloadCobranca> listarCobrancas(String cpf, String cnpj, Date dataInicio, Date dataFinal);
}

package com.fishcount.api.client.gerencianet.pix.cobranca.impl;

import com.fishcount.api.client.gerencianet.pix.cobranca.CobrancaPix;
import com.fishcount.common.model.classes.gerencianet.PayloadCobranca;

import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class CobrancaPixImpl implements CobrancaPix {

    @Override
    public PayloadCobranca criarCobranca(String txId, PayloadCobranca payloadCobranca) {
        return null;
    }

    @Override
    public PayloadCobranca revisarCobranca(PayloadCobranca payloadCobranca) {
        return null;
    }

    @Override
    public PayloadCobranca consultarCobranca(String txId) {
        return null;
    }

    @Override
    public PayloadCobranca criarCobrancaImediata(PayloadCobranca payloadCobranca) {
        return null;
    }

    @Override
    public List<PayloadCobranca> listarCobrancas(String cpf, String cnpj, Date dataInicio, Date dataFinal) {
        return null;
    }
}

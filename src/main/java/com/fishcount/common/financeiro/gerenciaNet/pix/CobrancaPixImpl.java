package com.fishcount.common.financeiro.gerenciaNet.pix;

import com.fishcount.common.financeiro.gerenciaNet.pix.interfaces.ICobrancaPix;
import com.fishcount.common.model.classes.gerencianet.PayloadCobranca;

import java.util.Date;
import java.util.List;

public class CobrancaPixImpl extends AbstractPix implements ICobrancaPix {

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

package com.fishcount.api.client.gerencianet.pix.cobranca.impl;

import com.fishcount.api.client.ClientConsumer;
import com.fishcount.api.client.gerencianet.pix.GenericPix;
import com.fishcount.api.client.gerencianet.pix.authentication.TokenPix;
import com.fishcount.api.client.gerencianet.pix.cobranca.CobrancaPix;
import com.fishcount.common.model.classes.gerencianet.PayloadCobranca;
import com.fishcount.common.utils.Utils;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CobrancaPixImpl extends GenericPix implements CobrancaPix {

    @Autowired
    private TokenPix tokenPix;

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
        String token = tokenPix.getBearerToken();

        RequestEntity<?> request = ClientConsumer.post()
                .setUrl(urlCobranca)
                .setBody(payloadCobranca)
                .addHeaders(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .addHeaders(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .doRequest();
        
        ResponseEntity<String> response = restTemplate.getRestConfig()
                .postForEntity(urlCobranca, request, String.class);
        
        if (response.hasBody() && HttpStatus.CREATED.equals(response.getStatusCode())){
            return Utils.jsonToObject(response.getBody(), PayloadCobranca.class);
        }

        return null;
    }

    @Override
    public List<PayloadCobranca> listarCobrancas(String cpf, String cnpj, Date dataInicio, Date dataFinal) {
        return null;
    }
}

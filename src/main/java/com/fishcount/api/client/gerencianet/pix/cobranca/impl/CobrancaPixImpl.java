package com.fishcount.api.client.gerencianet.pix.cobranca.impl;

import com.fishcount.api.client.ClientConsumer;
import com.fishcount.api.client.gerencianet.pix.GenericPix;
import com.fishcount.api.client.gerencianet.pix.authentication.TokenPix;
import com.fishcount.api.client.gerencianet.pix.cobranca.CobrancaPix;
import com.fishcount.common.exception.FcRuntimeException;
import com.fishcount.common.exception.enums.EnumFcInfraException;
import com.fishcount.common.model.classes.gerencianet.PayloadCobranca;
import com.fishcount.common.model.pattern.constants.HttpConstants;
import com.fishcount.common.utils.Utils;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CobrancaPixImpl extends GenericPix<PayloadCobranca> implements CobrancaPix {

    @Autowired
    private TokenPix tokenPix;

    private final String PARAM_CPF = "cpf";
    private final String PARAM_CNPJ = "cnpj";
    private final String PARAM_DATA_INICIO = "inicio";
    private final String PARAM_DATA_FIM = "fim";
    private final String PARAM_STATUS = "status";

    @Override
    public PayloadCobranca criarCobranca(String txId, PayloadCobranca payloadCobranca) {
        final String bearerToken = HttpConstants.BEARER_AUTH + tokenPix.getBearerToken();
        final RequestEntity<?> request = ClientConsumer.post()
                .setUrl(urlCobranca)
                .addParam(txId)
                .setBody(payloadCobranca)
                .addHeaders(HttpHeaders.AUTHORIZATION, bearerToken)
                .addHeaders(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .getRequest();

        final ResponseEntity<String> response = restTemplate.getRestConfig()
                .exchange(urlCobranca, HttpMethod.GET, request, String.class);

        validateResponse(response);
        if (isSuccessful(response)) {
            return Utils.jsonToObject(response.getBody(), PayloadCobranca.class);
        }
        return null;
    }

    @Override
    public PayloadCobranca revisarCobranca(PayloadCobranca payloadCobranca, String txId) {
        final String bearerToken = HttpConstants.BEARER_AUTH + tokenPix.getBearerToken();
        final RequestEntity<?> request = ClientConsumer.post()
                .setUrl(urlCobranca)
                .addParam(txId)
                .setBody(payloadCobranca)
                .addHeaders(HttpHeaders.AUTHORIZATION, bearerToken)
                .addHeaders(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .getRequest();

        final ResponseEntity<String> response = restTemplate.getRestConfig()
                .exchange(urlCobranca, HttpMethod.PATCH, request, String.class);

        validateResponse(response);
        if (isSuccessful(response)) {
            return Utils.jsonToObject(response.getBody(), PayloadCobranca.class);
        }
        return null;
    }

    @Override
    public PayloadCobranca consultarCobranca(String txId) {
        final String bearerToken = HttpConstants.BEARER_AUTH + tokenPix.getBearerToken();
        final RequestEntity<?> request = ClientConsumer.get()
                .setUrl(urlCobranca)
                .addParam(txId)
                .addHeaders(HttpHeaders.AUTHORIZATION, bearerToken)
                .addHeaders(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .getRequest();

        final ResponseEntity<String> response = restTemplate.getRestConfig()
                .exchange(urlCobranca, HttpMethod.GET, request, String.class);

        validateResponse(response);
        if (isSuccessful(response)) {
            return Utils.jsonToObject(response.getBody(), PayloadCobranca.class);
        }
        return null;
    }

    @Override
    public PayloadCobranca criarCobrancaImediata(PayloadCobranca payloadCobranca) {
        final String bearerToken = HttpConstants.BEARER_AUTH + tokenPix.getBearerToken();
        final RequestEntity<?> request = ClientConsumer.post()
                .setUrl(urlCobranca)
                .setBody(payloadCobranca)
                .addHeaders(HttpHeaders.AUTHORIZATION, bearerToken)
                .addHeaders(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .getRequest();

        final ResponseEntity<String> response = restTemplate.getRestConfig()
                .postForEntity(urlCobranca, request, String.class);

        validateResponse(response);
        if (isSuccessful(response)) {
            return Utils.jsonToObject(response.getBody(), PayloadCobranca.class);
        }
        return null;
    }

    @Override
    public List<PayloadCobranca> listarCobrancas(String cpf, String cnpj, Date dataInicio, Date dataFinal, String status) {
        final String bearerToken = HttpConstants.BEARER_AUTH + tokenPix.getBearerToken();
        final RequestEntity<?> request = ClientConsumer.get()
                .addQueryParam(PARAM_CPF, cpf)
                .addQueryParam(PARAM_CNPJ, cnpj)
                .addQueryParam(PARAM_DATA_INICIO, dataInicio)
                .addQueryParam(PARAM_DATA_FIM, dataFinal)
                .addQueryParam(PARAM_STATUS, status)
                .addHeaders(HttpHeaders.AUTHORIZATION, bearerToken)
                .addHeaders(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .getRequest();

        final ResponseEntity<String> response = restTemplate.getRestConfig()
                .exchange(urlCobranca, HttpMethod.GET, request, String.class);

        validateResponse(response);
        if (isSuccessful(response)) {
            return Utils.jsonToObject(response.getBody(), List.class);
        }
        return null;
    }

    private void validateResponse(final ResponseEntity<String> response) throws FcRuntimeException {
        if (isClientError(response) || isServerError(response)) {
            throw new FcRuntimeException(EnumFcInfraException.CHAMADA_HTTP_INCORRETA);
        }
    }
}

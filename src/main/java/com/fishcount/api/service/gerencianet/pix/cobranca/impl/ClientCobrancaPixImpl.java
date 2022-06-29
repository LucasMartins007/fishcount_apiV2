package com.fishcount.api.service.gerencianet.pix.cobranca.impl;

import com.fishcount.api.service.gerencianet.pix.GenericPix;
import com.fishcount.api.service.gerencianet.pix.cobranca.ClientCobrancaPix;
import com.fishcount.api.service.log.erros.PixHistoricoService;
import com.fishcount.common.exception.FcRuntimeException;
import com.fishcount.common.exception.enums.EnumFcInfraException;
import com.fishcount.common.model.classes.gerencianet.request.PayloadCobranca;
import com.fishcount.common.model.classes.gerencianet.response.PayloadCobrancaResponse;
import com.fishcount.common.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;

import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ClientCobrancaPixImpl extends GenericPix<PayloadCobranca> implements ClientCobrancaPix {

    private static final String PARAM_CPF = "cpf";
    private static final String PARAM_CNPJ = "cnpj";
    private static final String PARAM_DATA_INICIO = "inicio";
    private static final String PARAM_DATA_FIM = "fim";
    private static final String PARAM_STATUS = "status";

    private final PixHistoricoService pixHistoricoService;

    @Override
    public PayloadCobrancaResponse criarCobranca(String txId, PayloadCobranca payloadCobranca) {
        try {
            final RequestEntity<?> request = post()
                    .setUrl(urlCobranca)
                    .addParam(txId)
                    .setBody(payloadCobranca)
                    .addHeaders(HttpHeaders.AUTHORIZATION, getBearerToken())
                    .addHeaders(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .getRequest();

            final ResponseEntity<String> response = restTemplate.getRestConfig()
                    .exchange(urlCobranca, HttpMethod.GET, request, String.class);

            validateResponse(response);
            if (isSuccessful(response)) {
                return Utils.jsonToObject(response.getBody(), PayloadCobrancaResponse.class);
            }
            throw new FcRuntimeException(EnumFcInfraException.CHAMADA_HTTP_INCORRETA);
        } catch (RestClientException e) {
            throw new FcRuntimeException(EnumFcInfraException.CHAMADA_HTTP_INCORRETA);
        }
    }

    @Override
    public PayloadCobrancaResponse revisarCobranca(PayloadCobranca payloadCobranca, String txId) {
        try {
            final RequestEntity<?> request = post()
                    .setUrl(urlCobranca)
                    .addParam(txId)
                    .setBody(payloadCobranca)
                    .addHeaders(HttpHeaders.AUTHORIZATION, getBearerToken())
                    .addHeaders(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .getRequest();

            final ResponseEntity<String> response = restTemplate.getRestConfig()
                    .exchange(urlCobranca, HttpMethod.PATCH, request, String.class);

            validateResponse(response);
            if (isSuccessful(response)) {
                return Utils.jsonToObject(response.getBody(), PayloadCobrancaResponse.class);
            }
            throw new FcRuntimeException(EnumFcInfraException.CHAMADA_HTTP_INCORRETA);
        } catch (RestClientException e) {
            throw new FcRuntimeException(EnumFcInfraException.CHAMADA_HTTP_INCORRETA);
        }
    }

    @Override
    public PayloadCobrancaResponse consultarCobranca(String txId) {
        try {
            final RequestEntity<?> request = get()
                    .setUrl(urlCobranca)
                    .addParam(txId)
                    .addHeaders(HttpHeaders.AUTHORIZATION, getBearerToken())
                    .addHeaders(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .getRequest();

            final ResponseEntity<String> response = restTemplate.getRestConfig()
                    .exchange(urlCobranca, HttpMethod.GET, request, String.class);

            validateResponse(response);
            if (isSuccessful(response)) {
                return Utils.jsonToObject(response.getBody(), PayloadCobrancaResponse.class);
            }
            throw new FcRuntimeException(EnumFcInfraException.CHAMADA_HTTP_INCORRETA);
        } catch (RestClientException e) {
            throw new FcRuntimeException(EnumFcInfraException.CHAMADA_HTTP_INCORRETA);
        }
    }

    @Override
    public PayloadCobrancaResponse criarCobrancaImediata(PayloadCobranca payloadCobranca) {
        try {
            final RequestEntity<?> request = post()
                    .setUrl(urlCobranca)
                    .setBody(payloadCobranca)
                    .addHeaders(HttpHeaders.AUTHORIZATION, getBearerToken())
                    .addHeaders(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .getRequest();

            final ResponseEntity<String> response = restTemplate.getRestConfig()
                    .postForEntity(urlCobranca, request, String.class);

            validateResponse(response);

            return Utils.jsonToObject(response.getBody(), PayloadCobrancaResponse.class);
        } catch (RestClientException e) {
            pixHistoricoService.incluirCobrancaException(e, payloadCobranca, "criarCobrancaImediata");
            throw new FcRuntimeException(EnumFcInfraException.CHAMADA_HTTP_INCORRETA);
        }
    }

    @Override
    public List<PayloadCobrancaResponse> listarCobrancas(String cpf, String cnpj, Date dataInicio, Date dataFinal, String status) {
        try {
            final RequestEntity<?> request = get()
                    .addQueryParam(PARAM_CPF, cpf)
                    .addQueryParam(PARAM_CNPJ, cnpj)
                    .addQueryParam(PARAM_DATA_INICIO, dataInicio)
                    .addQueryParam(PARAM_DATA_FIM, dataFinal)
                    .addQueryParam(PARAM_STATUS, status)
                    .addHeaders(HttpHeaders.AUTHORIZATION, getBearerToken())
                    .addHeaders(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .getRequest();

            final ResponseEntity<String> response = restTemplate.getRestConfig()
                    .exchange(urlCobranca, HttpMethod.GET, request, String.class);

            validateResponse(response);
            if (isSuccessful(response)) {
                return Utils.jsonToObject(response.getBody(), List.class);
            }
            throw new FcRuntimeException(EnumFcInfraException.CHAMADA_HTTP_INCORRETA);
        } catch (RestClientException e) {
            throw new FcRuntimeException(EnumFcInfraException.CHAMADA_HTTP_INCORRETA);
        }
    }


}

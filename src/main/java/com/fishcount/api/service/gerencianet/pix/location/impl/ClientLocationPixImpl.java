package com.fishcount.api.service.gerencianet.pix.location.impl;

import com.fishcount.api.service.gerencianet.pix.GenericPix;
import com.fishcount.api.service.gerencianet.pix.location.ClientLocationPix;
import com.fishcount.common.exception.FcRuntimeException;
import com.fishcount.common.exception.enums.EnumFcInfraException;
import com.fishcount.common.model.classes.gerencianet.response.PayloadLocationResponse;
import com.fishcount.common.model.classes.gerencianet.response.PayloadQRCodeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ClientLocationPixImpl extends GenericPix<PayloadLocationResponse> implements ClientLocationPix {

    @Override
    public PayloadLocationResponse criarLocation(String tipoCobranca) {
        return null;
    }

    @Override
    public List<PayloadLocationResponse> consultarLocations(Date dataInicio, Date dataFim, boolean txIdPresente, String tipoCobranca) {
        return Collections.emptyList();
    }

    @Override
    public PayloadLocationResponse recuperarLocation(Integer locationId) {
        return null;
    }

    @Override
    public PayloadQRCodeResponse gerarQRCode(Integer locationId) {
        try {
            final RequestEntity<?> request = get()
                    .setUrl(urlLocation)
                    .addParam(locationId)
                    .addParam("qrcode")
                    .addHeaders(HttpHeaders.AUTHORIZATION, getBearerToken())
                    .addHeaders(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .getRequest();

            final ResponseEntity<PayloadQRCodeResponse> response = restTemplate.getRestConfig()
                    .exchange(request, PayloadQRCodeResponse.class);
            
            if (isSuccessful(response)) {
                return response.getBody();
            }
            
            throw new FcRuntimeException(EnumFcInfraException.CHAMADA_HTTP_INCORRETA);
        } catch (RestClientException e) {
            throw new FcRuntimeException(EnumFcInfraException.CHAMADA_HTTP_INCORRETA);
        }
    }

    @Override
    public void desvincularTxIdDaLocation(Integer locationId) {
        // TODO document why this method is empty
    }
}

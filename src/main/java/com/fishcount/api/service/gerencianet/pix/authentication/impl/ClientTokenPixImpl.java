package com.fishcount.api.service.gerencianet.pix.authentication.impl;

import com.fishcount.common.model.pattern.client.ClientConsumer;
import com.fishcount.api.service.gerencianet.pix.GenericPix;
import com.fishcount.common.exception.FcRuntimeException;
import com.fishcount.common.exception.enums.EnumFcHttpException;
import com.fishcount.common.model.classes.gerencianet.authentication.PayloadToken;
import com.fishcount.common.model.pattern.constants.HttpConstants;
import org.springframework.http.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import org.springframework.stereotype.Component;
import com.fishcount.api.service.gerencianet.pix.authentication.ClientTokenPix;

@Component
public class ClientTokenPixImpl extends GenericPix implements ClientTokenPix {

    @Override
    public String getBearerToken() {
        final PayloadToken token = getPayloadToken();

        return token.getAccess_token();
    }

    private PayloadToken getPayloadToken() {
        final String bearerToken = HttpConstants.BEARER_AUTH + getEncodedBasicAuth();
        
        final RequestEntity<?> requestConfig = ClientConsumer.post()
                .setUrl(urlToken)
                .addHeaders(HttpHeaders.AUTHORIZATION, bearerToken)
                .addHeaders(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .setBody(HttpConstants.CLIENT_CREDENTALS)
                .getRequest();

        final ResponseEntity<PayloadToken> response = restTemplate.getRestConfig()
                .postForEntity(urlToken, requestConfig, PayloadToken.class);

        if (isClientError(response) || isServerError(response)) {
            throw new FcRuntimeException(EnumFcHttpException.NAO_FOI_POSSIVEL_CAPTURAR_TOKEN_PIX);
        }
        return response.getBody();
    }

    private String getEncodedBasicAuth() {
        return Base64.getEncoder()
                .encodeToString((clientId + ":" + secretId)
                        .getBytes(StandardCharsets.UTF_8));
    }
}

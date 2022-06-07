package com.fishcount.api.client.gerencianet.pix.authentication.impl;

import com.fishcount.api.client.ClientConsumer;
import com.fishcount.api.client.gerencianet.pix.GenericPix;
import com.fishcount.api.client.gerencianet.pix.authentication.TokenPix;
import com.fishcount.common.exception.FcRuntimeException;
import com.fishcount.common.exception.enums.EnumFcHttpException;
import com.fishcount.common.model.classes.gerencianet.authentication.PayloadToken;
import com.fishcount.common.model.pattern.constants.HttpConstants;
import com.fishcount.common.utils.Utils;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import org.springframework.stereotype.Component;

@Component
public class TokenPixIml extends GenericPix implements TokenPix {

    @Override
    public String getBearerToken() {
        final PayloadToken token = getPayloadToken();

        return token.getAccess_token();
    }

    private PayloadToken getPayloadToken() {
        final RequestEntity<?> requestConfig = ClientConsumer.post()
                .setUrl(urlToken)
                .addHeaders(HttpHeaders.AUTHORIZATION, "Basic " + getEncodedBasicAuth())
                .addHeaders(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .setBody(HttpConstants.CLIENT_CREDENTALS)
                .getRequest();

        final ResponseEntity<PayloadToken> response = restTemplate.getRestConfig()
                .postForEntity(urlToken, requestConfig, PayloadToken.class);

        if (!HttpStatus.OK.equals(response.getStatusCode()) || Utils.isEmpty(response.getBody())) {
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

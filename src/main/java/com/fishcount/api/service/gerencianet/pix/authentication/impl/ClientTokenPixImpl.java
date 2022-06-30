package com.fishcount.api.service.gerencianet.pix.authentication.impl;

import com.fishcount.api.config.rest.RestTemplateConfiguration;
import com.fishcount.api.service.gerencianet.pix.authentication.ClientTokenPix;
import com.fishcount.common.exception.FcRuntimeException;
import com.fishcount.common.exception.enums.EnumFcHttpException;
import com.fishcount.common.model.classes.gerencianet.authentication.PayloadToken;
import com.fishcount.common.model.pattern.constants.HttpConstants;
import com.fishcount.common.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Base64;

@Component
public class ClientTokenPixImpl implements ClientTokenPix {

    @Value("${pix-config.url-access-token}")
    private String urlToken;

    @Value("${pix-config.client_id}")
    private String clientId;

    @Value("${pix-config.client_secret}")
    private String secretId;

    @Autowired
    private RestTemplateConfiguration restTemplate;

    @Override
    public String getBearerToken() {
        final PayloadToken token = getPayloadToken();

        return token != null ? token.getAccessToken() : null;
    }

    private PayloadToken getPayloadToken() {
        final RequestEntity<?> requestConfig = getRequestTokenConfiguration();
        final ResponseEntity<PayloadToken> response = restTemplate.getRestConfig()
                .postForEntity(urlToken, requestConfig, PayloadToken.class);

        if (!HttpStatus.OK.equals(response.getStatusCode()) || Utils.isEmpty(response.getBody())) {
            throw new FcRuntimeException(EnumFcHttpException.NAO_FOI_POSSIVEL_CAPTURAR_TOKEN_PIX);
        }
        return response.getBody();
    }

    private RequestEntity<?> getRequestTokenConfiguration() {
        try {

            final URI urlAccessToken = new URI(urlToken);

            final String clientCredentials = HttpConstants.CLIENT_CREDENTALS;
            final String basicAuth = getEncodedBasicAuth();

            final MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            headers.add(HttpHeaders.AUTHORIZATION, "Basic " + basicAuth);
            headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

            return new RequestEntity<>(clientCredentials, headers, HttpMethod.POST, urlAccessToken);

        } catch (URISyntaxException e) {
            throw new FcRuntimeException(EnumFcHttpException.NAO_FOI_POSSIVEL_CAPTURAR_TOKEN_PIX);
        }
    }

    private String getEncodedBasicAuth() {
        return Base64.getEncoder()
                .encodeToString((clientId + ":" + secretId)
                        .getBytes());
    }
}

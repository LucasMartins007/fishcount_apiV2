package com.fishcount.api.service.impl;

import com.fishcount.api.config.beans.RestTemplateBean;
import com.fishcount.api.service.PixService;
import com.fishcount.common.exception.FcRuntimeException;
import com.fishcount.common.exception.enums.EnumFcHttpException;
import com.fishcount.common.model.classes.gerencianet.authentication.PayloadToken;
import com.fishcount.common.model.pattern.constants.HttpConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class PixServiceImpl implements PixService {

    @Value("${pix-config.url-access-token}")
    private String urlToken;

    @Value("${pix-config.client_id}")
    private String clientId;

    @Value("${pix-config.client_secret}")
    private String secretId;

    @Autowired
    private RestTemplateBean restTemplate;

    @Override
    public String getBearerToken() {
        final RequestEntity<?> requestConfig = getRequestTokenConfiguration();
        final ResponseEntity<PayloadToken> response = restTemplate.getRestConfig().postForEntity(urlToken, requestConfig, PayloadToken.class);

        final PayloadToken token = response.getBody();
        if (!HttpStatus.OK.equals(response.getStatusCode()) || token == null) {
            throw new FcRuntimeException(EnumFcHttpException.NAO_FOI_POSSIVEL_CAPTURAR_TOKEN_PIX);
        }

        return token.getAccessToken();
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
            throw new FcRuntimeException(e);
        }
    }

    private String getEncodedBasicAuth() {
        return Base64.getEncoder()
                .encodeToString((clientId + ":" + secretId)
                        .getBytes(StandardCharsets.UTF_8));
    }


}

package com.fishcount.api.client.gerencianet.pix;

import com.fishcount.api.config.rest.RestTemplateConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class GenericPix<T> {

    @Value("${pix-config.url-access-token}")
    protected String urlToken;

    @Value("${pix-config.client_id}")
    protected String clientId;

    @Value("${pix-config.client_secret}")
    protected String secretId;

    @Value("${pix-config.base-url}")
    protected String baseUrl;

    @Value("${pix-config.url-cobranca}")
    protected String urlCobranca;

    @Autowired
    protected RestTemplateConfiguration restTemplate;

    protected boolean isSuccessful(ResponseEntity<?> response) {
        return response.hasBody()
                && (HttpStatus.CREATED.equals(response.getStatusCode())
                || HttpStatus.OK.equals(response.getStatusCode()));
    }

    protected boolean isClientError(ResponseEntity<?> response) {
        return HttpStatus.BAD_REQUEST.equals(response.getStatusCode());
    }

    protected boolean isServerError(ResponseEntity<?> response) {
        return HttpStatus.INTERNAL_SERVER_ERROR.equals(response.getStatusCode());
    }

    protected T getBody(ResponseEntity<T> response) {
        return response.getBody();
    }

}

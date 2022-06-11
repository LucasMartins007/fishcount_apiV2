package com.fishcount.api.service.gerencianet.pix;

import com.fishcount.api.config.rest.RestTemplateConfiguration;
import com.fishcount.api.service.gerencianet.pix.authentication.ClientTokenPix;
import com.fishcount.common.exception.FcRuntimeException;
import com.fishcount.common.exception.enums.EnumFcInfraException;
import com.fishcount.common.model.pattern.constants.HttpConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class GenericPix<T> {

    @Value("${pix-config.client_id}")
    protected String clientId;

    @Value("${pix-config.client_secret}")
    protected String secretId;

    @Value("${pix-config.base-url}")
    protected String baseUrl;

    @Value("${pix-config.url-cobranca}")
    protected String urlCobranca;
    
    @Value("${pix-config.url-location}")
    protected String urlLocation;

    @Autowired
    protected RestTemplateConfiguration restTemplate;
    
    @Autowired
    protected ClientTokenPix tokenPix;
    
    protected String getBearerToken(){
        return HttpConstants.BEARER_AUTH + tokenPix.getBearerToken();
    }

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
    
     protected void validateResponse(final ResponseEntity<String> response) throws FcRuntimeException {
        if (isClientError(response) || isServerError(response)) {
            throw new FcRuntimeException(EnumFcInfraException.CHAMADA_HTTP_INCORRETA);
        }
    }

}

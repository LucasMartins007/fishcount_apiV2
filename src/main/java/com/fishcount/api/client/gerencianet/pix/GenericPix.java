package com.fishcount.api.client.gerencianet.pix;

import com.fishcount.api.config.rest.RestTemplateConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public abstract class GenericPix {

    @Value("${pix-config.url-access-token}")
    protected  String urlToken;

    @Value("${pix-config.client_id}")
    protected  String clientId;

    @Value("${pix-config.client_secret}")
    protected  String secretId;
    
    @Value("${pix-config.base-url}")
    protected  String baseUrl;

    @Autowired
    protected  RestTemplateConfiguration restTemplate;

}

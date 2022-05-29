package com.fishcount.api.controller;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import com.fishcount.api.config.rest.RestTemplateConfiguration;
import com.fishcount.common.exception.FcRuntimeException;
import com.fishcount.common.model.classes.gerencianet.authentication.PayloadToken;
import com.fishcount.common.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lucas Martins
 */
@RestController
@RequestMapping(value = "api/v1/teste")
public class CobrancaPixController {

    @Autowired
    private RestTemplateConfiguration rest;

    @GetMapping
    public String teste() {
        try {

            final String input = "{\"grant_type\": \"client_credentials\"}";
            final URI url = new URI("https://api-pix-h.gerencianet.com.br/oauth/token");


            String clientId = "Client_Id_3e71852f85888c5f3817fd296e831d118de3c979";
            String clientSecret = "Client_Secret_b0e2f50a94392df2bb62fb572570345525626972";

            String basicAuth = Base64.getEncoder().encodeToString((clientId + ":" + clientSecret).getBytes(StandardCharsets.UTF_8));

            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            headers.add(HttpHeaders.AUTHORIZATION, "Basic " + basicAuth);
            headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);


            RequestEntity<?> request = new RequestEntity<>(input, headers, HttpMethod.POST, url);

            ResponseEntity<PayloadToken> p = rest.getRestConfig().postForEntity(url, request, PayloadToken.class);

            return p.toString();
        } catch (Exception e) {
            throw new FcRuntimeException(e);
        }


    }
}
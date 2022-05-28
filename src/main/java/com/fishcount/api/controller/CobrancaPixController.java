package com.fishcount.api.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Lucas Martins
 */
@RestController
@RequestMapping(value = "api/v1/teste")
public class CobrancaPixController {


    @GetMapping
    public void teste() throws FileNotFoundException, IOException, NoSuchAlgorithmException, CertificateException, KeyStoreException, UnrecoverableKeyException, KeyManagementException, URISyntaxException {
        final String input = "{\"grant_type\": \"client_credentials\"}";
        final URI url = new URI("https://api-pix-h.gerencianet.com.br/oauth/token");
        
        
        String clientId = "Client_Id_3e71852f85888c5f3817fd296e831d118de3c979";
        String clientSecret = "Client_Secret_b0e2f50a94392df2bb62fb572570345525626972";
        
        String basicAuth = clientId + ":" + clientSecret;
        
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Authorization", "Basic " + basicAuth);
        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        
        
        RequestEntity request = new RequestEntity(input, headers, HttpMethod.GET, url);
        
        RestTemplate rest = new RestTemplate();        
        Map b = rest.exchange(request, Map.class).getBody();
        
        
        

    }
}

@Setter
@Getter
class GrantType {

    private String grant_type;

    public GrantType(String grant_type) {
        this.grant_type = grant_type;
    }
}

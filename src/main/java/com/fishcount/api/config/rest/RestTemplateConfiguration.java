package com.fishcount.api.config.rest;

/**
 * @author Lucas Martins
 */

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import com.fishcount.common.exception.FcRuntimeException;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguration {

    @Value("${rest-template.key-store}")
    private String keyStoreFile;

    @Value("${rest-template.key-store-password}")
    private String keyStorePassword;

    @Value("${rest-template.key-store-type}")
    private String keyStoreType;

    @Value("${rest-template.protocol}")
    private String protocol;

    @Value("${rest-template.connection-timeout}")
    private Integer connectionTimeout;

    @Value("${rest-template.read-timeout}")
    private Integer readTimeout;

    @Bean
    public RestTemplate getRestConfig() {
        try {
            final KeyStore clientStore = KeyStore.getInstance(keyStoreType);
            final File file = ResourceUtils.getFile(keyStoreFile);
            clientStore.load(Files.newInputStream(file.toPath()), keyStorePassword.toCharArray());

            final SSLContextBuilder sslContextBuilder = new SSLContextBuilder();
            sslContextBuilder.setProtocol(protocol);
            sslContextBuilder.loadKeyMaterial(clientStore, keyStorePassword.toCharArray());
            sslContextBuilder.loadTrustMaterial(new TrustSelfSignedStrategy());

            final SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContextBuilder.build());
            final CloseableHttpClient httpClient = HttpClients
                    .custom()
                    .setSSLSocketFactory(sslConnectionSocketFactory)
                    .build();

            final HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
            requestFactory.setConnectTimeout(connectionTimeout);
            requestFactory.setReadTimeout(readTimeout);

            return new RestTemplate(requestFactory);

        } catch (KeyStoreException | UnrecoverableKeyException | CertificateException | IOException |
                 NoSuchAlgorithmException | KeyManagementException e) {
            throw new FcRuntimeException(e);
        }
    }
}

package com.fishcount.api.config.beans;

import com.fishcount.common.exception.FcRuntimeException;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.security.*;
import java.security.cert.CertificateException;

/**
 * @author Lucas Martins
 */
@Configuration
public class RestTemplateBean {

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
            ClassPathResource resource = new ClassPathResource(keyStoreFile);
            final URL url = resolverUrl(resource);
            final InputStream inputStream = resolverInputStream(url);

            final KeyStore clientStore = KeyStore.getInstance(keyStoreType);
            clientStore.load(inputStream, keyStorePassword.toCharArray());

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

        } catch (KeyStoreException | UnrecoverableKeyException | CertificateException | IOException
                | NoSuchAlgorithmException | KeyManagementException e) {
            throw new FcRuntimeException(e);
        }
    }

    private InputStream resolverInputStream(URL url) throws IOException {
        if (url != null) {
            return url.openStream();
        }
        File file = ResourceUtils.getFile(keyStoreFile);
        return Files.newInputStream(file.toPath());
    }

    private URL resolverUrl(ClassPathResource resource) {
        try {
            return resource.getURL();
        } catch (IOException e) {
            return null;
        }
    }
}

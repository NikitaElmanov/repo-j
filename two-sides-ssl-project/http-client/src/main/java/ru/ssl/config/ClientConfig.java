package ru.ssl.config;

import nl.altindag.ssl.SSLFactory;
import nl.altindag.ssl.apache5.util.Apache5SslUtils;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import static java.util.Objects.nonNull;

@Configuration
public class ClientConfig {

    @Bean
    public org.apache.hc.client5.http.impl.classic.CloseableHttpClient apache5HttpClient(@Autowired(required = false) SSLFactory sslFactory) {
        if (nonNull(sslFactory)) {
            org.apache.hc.client5.http.io.HttpClientConnectionManager connectionManager = PoolingHttpClientConnectionManagerBuilder.create()
                    .setSSLSocketFactory(Apache5SslUtils.toSocketFactory(sslFactory))
                    .build();

            return org.apache.hc.client5.http.impl.classic.HttpClients.custom()
                    .setConnectionManager(connectionManager)
                    .build();
        } else {
            return org.apache.hc.client5.http.impl.classic.HttpClients.createDefault();
        }
    }

    @Bean
    public RestTemplate restTemplate(org.apache.hc.client5.http.classic.HttpClient httpClient) {
        return new RestTemplate(new HttpComponentsClientHttpRequestFactory(httpClient));
    }

}

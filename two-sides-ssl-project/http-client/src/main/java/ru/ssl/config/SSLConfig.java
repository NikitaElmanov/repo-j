package ru.ssl.config;

import nl.altindag.ssl.SSLFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class SSLConfig {

    @Bean
    @Scope("prototype")
    public SSLFactory sslFactory(
            @Value("${ssl.one-way-authentication-enabled:false}") boolean oneWayAuthenticationEnabled,
            @Value("${ssl.two-way-authentication-enabled:false}") boolean twoWayAuthenticationEnabled,
            @Value("${ssl.key-store:}") String keyStorePath,
            @Value("${ssl.key-store-password:}") char[] keyStorePassword,
            @Value("${ssl.trust-store:}") String trustStorePath,
            @Value("${ssl.trust-store-password:}") char[] trustStorePassword) {
        SSLFactory sslFactory = null;

        if (oneWayAuthenticationEnabled) {
            sslFactory = SSLFactory.builder()
                    .withTrustMaterial(trustStorePath, trustStorePassword)
                    .withProtocols("TLSv1.2")
                    .build();
        }

        if (twoWayAuthenticationEnabled) {
            sslFactory = SSLFactory.builder()
                    .withIdentityMaterial(keyStorePath, keyStorePassword)
                    .withTrustMaterial(trustStorePath, trustStorePassword)
                    .withProtocols("TLSv1.2")
                    .build();
        }

        return sslFactory;
    }

}

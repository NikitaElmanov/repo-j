package com.example.nats.config;

import io.nats.client.Connection;
import io.nats.client.JetStream;
import io.nats.client.JetStreamManagement;
import io.nats.client.Nats;
import io.nats.client.api.RetentionPolicy;
import io.nats.client.api.StorageType;
import io.nats.client.api.StreamConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class NatsConfiguration {

    @Bean(destroyMethod = "close")
    Connection natsConnection(@Value("${nats.url}") String url)
            throws IOException, InterruptedException {
        return Nats.connect(url);
    }

    @Bean
    JetStreamManagement jetStreamManagement(Connection connection) throws IOException {
        return connection.jetStreamManagement();
    }

    @Bean
    JetStream jetStream(Connection connection) throws IOException {
        return connection.jetStream();
    }

    @Bean
    StreamInitializer streamInitializer(
            JetStreamManagement management,
            @Value("${nats.stream}") String stream,
            @Value("${nats.subject}") String subject) {
        return new StreamInitializer(management, stream, subject);
    }

    public static final class StreamInitializer {
        public StreamInitializer(JetStreamManagement management, String stream, String subject) {
            try {
                management.getStreamInfo(stream);
            } catch (Exception notFound) {
                try {
                    management.addStream(StreamConfiguration.builder()
                            .name(stream)
                            .subjects(subject)
                            .storageType(StorageType.File)
                            .retentionPolicy(RetentionPolicy.Limits)
                            .build());
                } catch (Exception e) {
                    throw new IllegalStateException("Cannot initialize NATS stream " + stream, e);
                }
            }
        }
    }
}

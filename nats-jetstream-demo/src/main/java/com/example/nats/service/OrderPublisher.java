package com.example.nats.service;

import com.example.nats.events.OrderEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.nats.client.JetStream;
import io.nats.client.impl.Headers;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderPublisher {

    private final JetStream jetStream;
    private final ObjectMapper objectMapper;

    @Value("${nats.subject}")
    private String subject;

    public OrderEvent publish(long orderId) {
        try {
            OrderEvent event = new OrderEvent(UUID.randomUUID().toString(), orderId, Instant.now());

            Headers headers = new Headers();
            headers.put("Nats-Msg-Id", event.eventId());

            jetStream.publish(
                    subject,
                    headers,
                    objectMapper.writeValueAsString(event)
                            .getBytes(StandardCharsets.UTF_8));

            return event;
        } catch (Exception e) {
            throw new IllegalStateException("Cannot publish order event", e);
        }
    }
}

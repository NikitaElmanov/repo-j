package com.example.nats.service;

import com.example.nats.events.OrderEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.nats.client.JetStream;
import io.nats.client.JetStreamSubscription;
import io.nats.client.Message;
import io.nats.client.PullSubscribeOptions;
import io.nats.client.api.AckPolicy;
import io.nats.client.api.ConsumerConfiguration;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
@DependsOn("streamInitializer")
public class OrderConsumer {

    private final JetStream jetStream;
    private final ObjectMapper objectMapper;
    private final Set<String> processedEvents = ConcurrentHashMap.newKeySet();
    private volatile boolean running;
    private JetStreamSubscription subscription;

    @Value("${nats.consumer}")
    private String consumer;

    @Value("${nats.subject}")
    private String subject;

    @PostConstruct
    void start() throws Exception {
        ConsumerConfiguration configuration = ConsumerConfiguration.builder()
                .durable(consumer)
                .ackPolicy(AckPolicy.Explicit)
                .ackWait(Duration.ofSeconds(30))
                .maxDeliver(5)
                .build();

        PullSubscribeOptions options = PullSubscribeOptions.builder()
                .durable(consumer)
                .configuration(configuration)
                .build();

        subscription = jetStream.subscribe(subject, options);
        running = true;
        Thread.startVirtualThread(this::consumeLoop);
    }

    private void consumeLoop() {
        while (running) {
            try {
                for (Message message : subscription.fetch(10, Duration.ofSeconds(2))) {
                    handle(message);
                }
            } catch (Exception e) {
                if (running) {
                    System.err.println("NATS consumer error: " + e.getMessage());
                }
            }
        }
    }

    private void handle(Message message) {
        try {
            OrderEvent event = objectMapper.readValue(message.getData(), OrderEvent.class);

            // В production это должно быть таблицей processed_events в PostgreSQL.
            if (processedEvents.add(event.eventId())) {
                System.out.printf("Processed order %d, event %s%n",
                        event.orderId(), event.eventId());
                processBusinessOperation(event);
            } else {
                System.out.println("Duplicate event skipped: " + event.eventId());
            }

            message.ack();
        } catch (Exception e) {
            System.err.println("Order processing failed: " + e.getMessage());
            message.nak();
        }
    }

    private void processBusinessOperation(OrderEvent event) {
        // Здесь обычно находится транзакция с бизнес-изменениями.
    }

    @PreDestroy
    void stop() throws Exception {
        running = false;
        if (subscription != null) {
            subscription.unsubscribe();
        }
    }
}

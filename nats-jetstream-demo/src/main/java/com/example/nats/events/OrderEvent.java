package com.example.nats.events;

import java.time.Instant;

public record OrderEvent(
        String eventId,
        long orderId,
        Instant createdAt
) {
}

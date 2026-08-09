package com.example.nats.controller;

import com.example.nats.events.OrderEvent;
import com.example.nats.service.OrderPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderPublisher publisher;

    @PostMapping
    public ResponseEntity<OrderEvent> create(@RequestParam long orderId) {
        return ResponseEntity.accepted().body(publisher.publish(orderId));
    }
}

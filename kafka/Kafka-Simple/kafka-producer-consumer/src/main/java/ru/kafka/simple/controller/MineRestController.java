package ru.kafka.simple.controller;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.kafka.simple.model.Person;
import ru.kafka.simple.producer.MineKafkaProducer;

import static lombok.AccessLevel.PRIVATE;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class MineRestController {

    MineKafkaProducer kafkaProducer;

    @PostMapping("/send")
    public ResponseEntity sendJson(@RequestBody Person person) {

        kafkaProducer.sendMessage(person);

        return ResponseEntity.ok().build();
    }
}

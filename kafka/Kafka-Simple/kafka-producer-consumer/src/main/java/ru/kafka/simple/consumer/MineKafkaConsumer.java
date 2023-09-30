package ru.kafka.simple.consumer;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.kafka.simple.model.Person;

import static lombok.AccessLevel.PRIVATE;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class MineKafkaConsumer {

    @KafkaListener(topics = "${topics}", containerFactory = "kafkaListenerContainerFactory")
    public void listen(Person person) {
        log.info("Consumer get new \"person\" message: {}", person);
    }
}

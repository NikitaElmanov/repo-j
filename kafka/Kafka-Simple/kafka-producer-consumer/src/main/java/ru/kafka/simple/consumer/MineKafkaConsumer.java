package ru.kafka.simple.consumer;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.kafka.simple.model.Person;

import static lombok.AccessLevel.PRIVATE;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class MineKafkaConsumer {

    @KafkaListener(topics = "${topics}",
            containerFactory = "kafkaListenerContainerFactory"/*,
            groupId = "${spring.kafka.consumer.group-id}"*/)
    public void listen(ConsumerRecord<String, Person> record) {
        log.info("Consumer get new \"person\", key: {}, message: {}", record.key(), record.value());
    }
}
